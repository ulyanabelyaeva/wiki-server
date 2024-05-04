package wiki.service.page.api;

import wiki.dto.page.UpdatePageDto;

public interface PageUpdater {

    void update(UpdatePageDto request);
}
