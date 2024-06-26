package wiki.service.page.api;

import wiki.dto.page.PageAdvancedDto;
import wiki.dto.page.PageDto;
import wiki.model.Page;

public interface PageMapper {

    PageDto toDto(Page page);

    PageAdvancedDto toAdvancedDto(Page page);
}
