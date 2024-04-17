package wiki.service.directory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.model.Directory;
import wiki.repository.DirectoryRepository;
import wiki.service.directory.api.DirectoryStore;

import java.util.List;
import java.util.function.Function;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Service
public class DirectoryStoreImpl implements DirectoryStore {

    private final DirectoryRepository directoryRepository;

    public DirectoryStoreImpl(DirectoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public void saveDirectory(Directory directory) {
        directoryRepository.save(directory);
    }

    @Override
    @Transactional(readOnly = true)
    public <T> T readDirectory(Long id,
                               Function<Directory, T> mapper) {
        Directory directory = directoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Directory not found by id"));
        return mapper.apply(directory);
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public List<Directory> readRootDirectories() {
        return directoryRepository.findByParentDirectoryIsNull();
    }
}
