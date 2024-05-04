package wiki.service.directory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.directory.UpdateDirectoryDto;
import wiki.model.Directory;
import wiki.service.directory.api.DirectoryStore;
import wiki.service.directory.api.DirectoryUpdater;

import static java.util.function.Function.identity;

@Service
public class DirectoryUpdateImpl implements DirectoryUpdater {

    private final DirectoryStore directoryStore;

    public DirectoryUpdateImpl(DirectoryStore directoryStore) {
        this.directoryStore = directoryStore;
    }

    @Override
    @Transactional
    public void update(UpdateDirectoryDto request) {
        long id = Long.parseLong(request.getId());
        Directory directory = directoryStore.readDirectory(id, identity());
        directory.setName(request.getName());
        directory.setUpdatedAt(request.getUpdatedAt());
    }
}
