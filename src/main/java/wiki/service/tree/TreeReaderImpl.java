package wiki.service.tree;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.directory.DirectoryDto;
import wiki.dto.directory.TreeDto;
import wiki.model.Directory;
import wiki.service.directory.api.DirectoryMapper;
import wiki.service.directory.api.DirectoryStore;
import wiki.service.tree.api.TreeReader;

import java.util.List;

@Service
public class TreeReaderImpl implements TreeReader {

    private final DirectoryMapper directoryMapper;
    private final DirectoryStore directoryStore;

    public TreeReaderImpl(DirectoryMapper directoryMapper,
                          DirectoryStore directoryStore) {
        this.directoryMapper = directoryMapper;
        this.directoryStore = directoryStore;
    }

    //todo + получить корневые страницы

    @Override
    @Transactional(readOnly = true)
    public TreeDto read() {
        List<Directory> rootDirectories = directoryStore.readRootDirectories();
        List<DirectoryDto> directoryDtoList = rootDirectories.stream()
                .map(directoryMapper::toDto)
                .toList();
        TreeDto response = new TreeDto();
        response.setDirectories(directoryDtoList);
        return response;
    }
}
