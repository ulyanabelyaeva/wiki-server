package wiki.service.tag.api;

import wiki.dto.tag.TagDto;

import java.util.List;

public interface TagReader {

    List<TagDto> readTags();
}
