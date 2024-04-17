package wiki.service.directory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.directory.NewDictionaryDto;
import wiki.model.Directory;
import wiki.model.User;
import wiki.repository.UserRepository;
import wiki.service.directory.api.DirectoryCreator;
import wiki.service.directory.api.DirectoryStore;

import static java.util.Objects.nonNull;
import static java.util.function.Function.identity;

@Service
public class DirectoryCreatorImpl implements DirectoryCreator {

    private final DirectoryStore directoryStore;
    private final UserRepository userRepository;

    public DirectoryCreatorImpl(DirectoryStore directoryStore,
                                UserRepository userRepository) {
        this.directoryStore = directoryStore;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Long create(NewDictionaryDto request) {
        Directory directory = new Directory();
        directory.setOwner(this.getOwner());
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

    //todo перделать с появлением авторизации
    private User getOwner() {
        return userRepository.findById(1L)
                .orElseThrow(() -> new IllegalStateException("Default user not created"));
    }
}
