package wiki.service.page.api;

import wiki.dto.page.NewPageDto;

public interface PageCreator {

    Long create(NewPageDto request);
}
