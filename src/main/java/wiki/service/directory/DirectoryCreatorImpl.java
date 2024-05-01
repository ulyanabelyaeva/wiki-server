package wiki.service.directory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.directory.NewDictionaryDto;
import wiki.model.Directory;
import wiki.service.directory.api.DirectoryCreator;
import wiki.service.directory.api.DirectoryStore;
import wiki.service.user.api.UserService;

import static java.util.Objects.nonNull;
import static java.util.function.Function.identity;

@Service
public class DirectoryCreatorImpl implements DirectoryCreator {

    private final DirectoryStore directoryStore;
    private final UserService userService;

    public DirectoryCreatorImpl(DirectoryStore directoryStore,
                                UserService userService) {
        this.directoryStore = directoryStore;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Long create(NewDictionaryDto request) {
        Directory directory = new Directory();
        directory.setOwner(userService.getCurrentUser());
        if (nonNull(request.getParentDirectoryId())) {
            long parentDirectoryId = Long.parseLong(request.getParentDirectoryId());
            Directory parentDirectory = directoryStore.readDirectory(parentDirectoryId, identity());
            directory.setParentDirectory(parentDirectory);
        }
        directory.setName(request.getName());
        directory.setCreatedAt(request.getCreatedAt());
        directory.setUpdatedAt(request.getCreatedAt());

        directoryStore.saveDirectory(directory);
        return directory.getId();
    }


}
