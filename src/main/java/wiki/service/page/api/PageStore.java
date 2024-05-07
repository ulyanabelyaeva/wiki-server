package wiki.service.page.api;

import wiki.model.Page;
import wiki.model.User;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public interface PageStore {

    void savePage(Page page);

    <T> T readPage(Long id,
                   Function<Page, T> mapper);

    <T> T readPage(UUID uuid,
                   Function<Page, T> mapper);

    List<Page> readRootPages(User user);
}
