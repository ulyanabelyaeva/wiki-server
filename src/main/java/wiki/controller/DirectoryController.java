package wiki.controller;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.dto.directory.DirectoryDto;
import wiki.dto.directory.NewDictionaryDto;
import wiki.dto.directory.UpdateDirectoryDto;
import wiki.service.directory.api.DirectoryCreator;
import wiki.service.directory.api.DirectoryMapper;
import wiki.service.directory.api.DirectoryStore;
import wiki.service.directory.api.DirectoryUpdater;

import javax.validation.Valid;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/directory")
public class DirectoryController {

    private static final Logger LOGGER = getLogger(DirectoryController.class);

    private final DirectoryCreator directoryCreator;
    private final DirectoryUpdater directoryUpdater;
    private final DirectoryMapper directoryMapper;
    private final DirectoryStore directoryStore;

    public DirectoryController(DirectoryCreator directoryCreator,
                               DirectoryUpdater directoryUpdater,
                               DirectoryMapper directoryMapper,
                               DirectoryStore directoryStore) {
        this.directoryCreator = directoryCreator;
        this.directoryUpdater = directoryUpdater;
        this.directoryMapper = directoryMapper;
        this.directoryStore = directoryStore;
    }

    @PostMapping("/create")
    public ResponseEntity<DirectoryDto> createNewDirectory(
            @RequestBody
            @Valid
            NewDictionaryDto request
    ) {
        LOGGER.info("Create DIRECTORY requested: {}", request);
        Long id = directoryCreator.create(request);
        DirectoryDto response = directoryStore.readDirectory(
                id,
                directoryMapper::toDto
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<DirectoryDto> updateDirectory(
            @RequestBody
            @Valid
            UpdateDirectoryDto request
    ) {
        LOGGER.info("Update DIRECTORY requested: {}", request);
        directoryUpdater.update(request);
        DirectoryDto response = directoryStore.readDirectory(
                Long.parseLong(request.getId()),
                directoryMapper::toDto
        );
        return ResponseEntity.ok(response);
    }
}
