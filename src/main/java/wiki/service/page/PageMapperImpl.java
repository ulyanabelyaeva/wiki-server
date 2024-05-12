package wiki.service.page;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.page.PageAdvancedDto;
import wiki.dto.page.PageDto;
import wiki.dto.tag.TagDto;
import wiki.model.Page;
import wiki.model.PageTag;
import wiki.service.page.api.PageMapper;
import wiki.service.page.api.PageStore;
import wiki.service.tag.api.TagMapper;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Component
@Transactional(propagation = MANDATORY)
public class PageMapperImpl implements PageMapper {

    private final PageStore pageStore;
    private final TagMapper tagMapper;

    public PageMapperImpl(PageStore pageStore,
                          TagMapper tagMapper) {
        this.pageStore = pageStore;
        this.tagMapper = tagMapper;
    }

    @Override
    public PageDto toDto(Page page) {
        PageDto dto = new PageDto();
        this.fillCommon(page, dto);
        return dto;
    }

    @Override
    public PageAdvancedDto toAdvancedDto(Page page) {
        PageAdvancedDto dto = new PageAdvancedDto();
        this.fillCommon(page, dto);
        List<TagDto> tagsDto = this.createTagsDto(page);
        dto.setTags(tagsDto);
        return dto;
    }

    private <T extends PageDto> void fillCommon(Page page, T dto) {
        dto.setId(String.valueOf(page.getId()));
        dto.setName(page.getName());
        dto.setFileUUID(page.getFileUUID());
        dto.setCreatedAt(page.getCreatedAt());
        dto.setUpdatedAt(page.getUpdatedAt());
    }

    private List<TagDto> createTagsDto(Page page) {
        return pageStore.readTagRelations(page).stream()
                .map(PageTag::getTag)
                .map(tagMapper::toDto)
                .toList();
    }
}
