package wiki.service.page;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.page.PageDto;
import wiki.model.Page;
import wiki.service.page.api.PageMapper;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Component
@Transactional(propagation = MANDATORY)
public class PageMapperImpl implements PageMapper {

    @Override
    public PageDto toDto(Page page) {
        PageDto pageDto = new PageDto();
        pageDto.setId(String.valueOf(page.getId()));
        pageDto.setName(page.getName());
        pageDto.setFilePath(page.getFilePath());
        pageDto.setFileUUID(page.getFileUUID());
        pageDto.setCreatedAt(page.getCreatedAt());
        pageDto.setUpdatedAt(page.getUpdatedAt());
        return pageDto;
    }
}
