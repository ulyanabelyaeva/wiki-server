package wiki.service.directory.api;

import wiki.dto.directory.NewDictionaryDto;

public interface DirectoryCreator {

    Long create( NewDictionaryDto request);
}
