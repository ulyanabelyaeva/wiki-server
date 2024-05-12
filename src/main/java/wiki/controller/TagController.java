package wiki.controller;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wiki.dto.tag.NewTagDto;
import wiki.dto.tag.TagDto;
import wiki.service.tag.api.TagCreator;
import wiki.service.tag.api.TagMapper;
import wiki.service.tag.api.TagReader;
import wiki.service.tag.api.TagStore;

import javax.validation.Valid;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/tag")
public class TagController {

    private static final Logger LOGGER = getLogger(TagController.class);

    private final TagCreator tagCreator;
    private final TagReader tagReader;
    private final TagMapper tagMapper;
    private final TagStore tagStore;

    public TagController(TagCreator tagCreator,
                         TagReader tagReader,
                         TagMapper tagMapper,
                         TagStore tagStore) {
        this.tagCreator = tagCreator;
        this.tagReader = tagReader;
        this.tagMapper = tagMapper;
        this.tagStore = tagStore;
    }

    @PostMapping("/create")
    public ResponseEntity<TagDto> createTag(
            @RequestBody
            @Valid
            NewTagDto request
    ) {
        LOGGER.info("Create TAG requested: {}", request);
        Long tagId = tagCreator.create(request);
        TagDto response = tagStore.readTag(
                tagId,
                tagMapper::toDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/read")
    public ResponseEntity<List<TagDto>> readTags() {
        LOGGER.info("Create user TAGs requested");
        List<TagDto> response = tagReader.readTags();
        return ResponseEntity.ok(response);
    }
}
