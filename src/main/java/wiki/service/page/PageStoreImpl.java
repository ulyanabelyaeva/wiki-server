package wiki.service.page;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.model.Page;
import wiki.model.PageTag;
import wiki.model.User;
import wiki.repository.PageRepository;
import wiki.repository.PageTagRepository;
import wiki.service.page.api.PageStore;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Service
public class PageStoreImpl implements PageStore {

    private final PageTagRepository pageTagRepository;
    private final PageRepository pageRepository;

    public PageStoreImpl(PageTagRepository pageTagRepository,
                         PageRepository pageRepository) {
        this.pageTagRepository = pageTagRepository;
        this.pageRepository = pageRepository;
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public void savePage(Page page) {
        pageRepository.save(page);
    }

    @Override
    @Transactional(readOnly = true)
    public <T> T readPage(Long id,
                          Function<Page, T> mapper) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Page not found by id"));
        return mapper.apply(page);
    }

    @Override
    @Transactional(readOnly = true)
    public <T> T readPage(UUID uuid,
                          Function<Page, T> mapper) {
        Page page = pageRepository.findByFileUUID(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Page not found by uuid"));
        return mapper.apply(page);
    }

    @Override
    @Transactional(readOnly = true, propagation = MANDATORY)
    public List<Page> readRootPages(User user) {
        return pageRepository.findByDirectoryIsNullAndOwner(user);
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public List<PageTag> readTagRelations(Page page) {
        return pageTagRepository.findByPage(page);
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public void savePageTags(List<PageTag> tagRelations) {
        pageTagRepository.saveAll(tagRelations);
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public void deletePageTags(List<PageTag> tagRelations) {
        pageTagRepository.deleteAll(tagRelations);
    }
}
