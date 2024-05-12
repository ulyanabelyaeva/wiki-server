package wiki.service.tag.api;

import wiki.dto.tag.NewTagDto;

public interface TagCreator {

    Long create(NewTagDto request);
}
