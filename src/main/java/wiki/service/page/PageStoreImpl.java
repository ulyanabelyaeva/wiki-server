package wiki.service.page;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.model.Page;
import wiki.model.User;
import wiki.repository.PageRepository;
import wiki.service.page.api.PageStore;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Service
public class PageStoreImpl implements PageStore {

    private final PageRepository pageRepository;

    public PageStoreImpl(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public void savePage(Page page) {
        pageRepository.save(page);
    }

    @Override
    @Transactional(readOnly = true)
    public <T> T readPage(Long id,
                          Function<Page, T> mapper) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Page not found by id"));
        return mapper.apply(page);
    }

    @Override
    @Transactional(readOnly = true)
    public <T> T readPage(UUID uuid,
                          Function<Page, T> mapper) {
        Page page = pageRepository.findByFileUUID(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Page not found by uuid"));
        return mapper.apply(page);
    }

    @Override
    @Transactional(readOnly = true, propagation = MANDATORY)
    public List<Page> readRootPages(User user) {
        return pageRepository.findByDirectoryIsNullAndOwner(user);
    }
}
