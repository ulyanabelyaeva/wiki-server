package wiki.service.page;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.page.UpdatePageDto;
import wiki.model.Page;
import wiki.service.page.api.PageStore;
import wiki.service.page.api.PageUpdater;

import static java.util.function.Function.identity;

@Service
public class PageUpdateImpl implements PageUpdater {

    private final PageStore pageStore;

    public PageUpdateImpl(PageStore pageStore) {
        this.pageStore = pageStore;
    }

    @Override
    @Transactional
    public void update(UpdatePageDto request) {
        long id = Long.parseLong(request.getId());
        Page page = pageStore.readPage(id, identity());
        page.setName(request.getName());
        page.setUpdatedAt(request.getUpdatedAt());
    }
}
