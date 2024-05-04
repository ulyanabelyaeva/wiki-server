package wiki.service.directory.api;

import wiki.dto.directory.UpdateDirectoryDto;

public interface DirectoryUpdater {

    void update(UpdateDirectoryDto request);
}
