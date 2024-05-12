package wiki.service.tag.api;

import wiki.dto.tag.TagDto;
import wiki.model.Tag;

public interface TagMapper {

    TagDto toDto(Tag tag);
}
