package wiki.service.directory.api;

import wiki.dto.directory.DirectoryDto;
import wiki.model.Directory;

public interface DirectoryMapper {

    DirectoryDto toDto(Directory directory);
}
