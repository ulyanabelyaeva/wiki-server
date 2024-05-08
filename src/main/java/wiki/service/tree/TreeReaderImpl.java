package wiki.service.tree;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.directory.DirectoryDto;
import wiki.dto.directory.TreeDto;
import wiki.dto.page.PageDto;
import wiki.model.Directory;
import wiki.model.Page;
import wiki.model.User;
import wiki.service.directory.api.DirectoryMapper;
import wiki.service.directory.api.DirectoryStore;
import wiki.service.page.api.PageMapper;
import wiki.service.page.api.PageStore;
import wiki.service.tree.api.TreeReader;
import wiki.service.user.api.AuthService;

import java.util.Comparator;
import java.util.List;

@Service
public class TreeReaderImpl implements TreeReader {

    private final DirectoryMapper directoryMapper;
    private final DirectoryStore directoryStore;
    private final AuthService authService;
    private final PageMapper pageMapper;
    private final PageStore pageStore;

    public TreeReaderImpl(DirectoryMapper directoryMapper,
                          DirectoryStore directoryStore,
                          AuthService authService,
                          PageMapper pageMapper,
                          PageStore pageStore) {
        this.directoryMapper = directoryMapper;
        this.directoryStore = directoryStore;
        this.authService = authService;
        this.pageMapper = pageMapper;
        this.pageStore = pageStore;
    }

    @Override
    @Transactional(readOnly = true)
    public TreeDto read() {
        User owner = authService.getCurrentUser();
        List<Directory> rootDirectories = directoryStore.readRootDirectories(owner);
        List<Page> pages = pageStore.readRootPages(owner);
        List<DirectoryDto> directoryDtoList = rootDirectories.stream()
                .sorted(Comparator.comparing(Directory::getId))
                .map(directoryMapper::toDto)
                .toList();
        List<PageDto> pageDtoList = pages.stream()
                .sorted(Comparator.comparing(Page::getId))
                .map(pageMapper::toDto)
                .toList();
        TreeDto response = new TreeDto();
        response.setDirectories(directoryDtoList);
        response.setPages(pageDtoList);
        return response;
    }
}
