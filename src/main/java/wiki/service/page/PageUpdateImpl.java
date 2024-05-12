package wiki.service.page;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.page.UpdatePageDto;
import wiki.model.Page;
import wiki.model.PageTag;
import wiki.model.Tag;
import wiki.service.minio.api.MinioService;
import wiki.service.page.api.PageStore;
import wiki.service.page.api.PageUpdater;
import wiki.service.tag.api.TagStore;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@Service
public class PageUpdateImpl implements PageUpdater {

    private final MinioService minioService;
    private final PageStore pageStore;
    private final TagStore tagStore;

    public PageUpdateImpl(MinioService minioService,
                          PageStore pageStore,
                          TagStore tagStore) {
        this.minioService = minioService;
        this.pageStore = pageStore;
        this.tagStore = tagStore;
    }

    @Override
    @Transactional
    public void update(UpdatePageDto request) {
        long id = Long.parseLong(request.getId());
        Page page = pageStore.readPage(id, identity());
        page.setName(request.getName());
        page.setUpdatedAt(request.getUpdatedAt());

        minioService.updateFile(page.getFileUUID(), request.getContent());
        this.updateTags(page, request.getTagIds());
    }

    private void updateTags(Page page,
                            List<String> tagIds) {
        if (tagIds.isEmpty()) {
            return;
        }
        List<PageTag> tagsRelations = pageStore.readTagRelations(page);
        Set<Long> existedTagIds = tagsRelations
                .stream()
                .map(PageTag::getTag)
                .map(Tag::getId)
                .collect(Collectors.toSet());
        Set<Long> tagIdsFromRequest = tagIds.stream().map(Long::parseLong).collect(Collectors.toSet());

        Set<Long> tagsForRemoval = new HashSet<>();
        Set<Long> tagsForCreation = new HashSet<>();
        for (Long existedTagId : existedTagIds) {
            if (!tagIdsFromRequest.contains(existedTagId)) {
                tagsForRemoval.add(existedTagId);
            }
        }
        for (Long tagIdFromRequest : tagIdsFromRequest) {
            if (!existedTagIds.contains(tagIdFromRequest)) {
                tagsForCreation.add(tagIdFromRequest);
            }
        }

        this.createTagRelations(tagsForCreation, page);
        this.removeTagRelation(tagsForRemoval, tagsRelations);
    }

    private void createTagRelations(Set<Long> tagsForCreation,
                                    Page page) {
        List<PageTag> pageTags = tagsForCreation.stream()
                .map(tagId -> {
                    PageTag pageTag = new PageTag();
                    pageTag.setPage(page);
                    Tag tag = tagStore.readTag(tagId, identity());
                    pageTag.setTag(tag);
                    return pageTag;
                }).toList();
        pageStore.savePageTags(pageTags);
    }

    private void removeTagRelation(Set<Long> tagsForRemoval,
                                   List<PageTag> tagsRelations) {
        List<PageTag> relations = tagsRelations.stream()
                .filter(pageTag -> tagsForRemoval.contains(pageTag.getTag().getId()))
                .toList();
        pageStore.deletePageTags(relations);
    }
}
