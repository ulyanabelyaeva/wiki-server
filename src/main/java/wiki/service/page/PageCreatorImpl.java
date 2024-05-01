package wiki.service.page;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.page.NewPageDto;
import wiki.model.Directory;
import wiki.model.Page;
import wiki.service.directory.api.DirectoryStore;
import wiki.service.page.api.PageCreator;
import wiki.service.page.api.PageStore;
import wiki.service.user.api.UserService;

import java.util.function.Function;

import static java.util.Objects.isNull;

@Service
public class PageCreatorImpl implements PageCreator {

    private final DirectoryStore directoryStore;
    private final UserService userService;
    private final PageStore pageStore;

    public PageCreatorImpl(DirectoryStore directoryStore,
                           UserService userService,
                           PageStore pageStore) {
        this.directoryStore = directoryStore;
        this.userService = userService;
        this.pageStore = pageStore;
    }

    @Override
    @Transactional
    public Long create(NewPageDto request) {
        if (isNull(request.getDirectoryId())) {
            return this.createRootPage(request);
        } else {
            return this.createPage(request);
        }
    }

    private Long createRootPage(NewPageDto request) {
        Page page = createModel(request);
        pageStore.savePage(page);
        return page.getId();
    }

    private Long createPage(NewPageDto request) {
        Directory directory = directoryStore.readDirectory(
                Long.parseLong(request.getDirectoryId()),
                Function.identity());

        Page page = createModel(request);
        page.setDirectory(directory);
        pageStore.savePage(page);
        return page.getId();
    }

    private Page createModel(NewPageDto request) {
        Page page = new Page();
        page.setOwner(userService.getCurrentUser());
        page.setName(request.getName());
        page.setCreatedAt(request.getCreatedAt());
        page.setUpdatedAt(request.getCreatedAt());
        return page;
    }
}
