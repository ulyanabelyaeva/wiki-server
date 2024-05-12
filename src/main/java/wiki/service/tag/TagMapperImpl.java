package wiki.service.tag;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.tag.TagDto;
import wiki.model.Tag;
import wiki.service.tag.api.TagMapper;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Component
@Transactional(propagation = MANDATORY)
public class TagMapperImpl implements TagMapper {

    @Override
    public TagDto toDto(Tag tag) {
        TagDto dto = new TagDto();
        dto.setId(String.valueOf(tag.getId()));
        dto.setName(tag.getName());
        return dto;
    }
}
