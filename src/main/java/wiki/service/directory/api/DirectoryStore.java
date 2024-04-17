package wiki.service.directory.api;

import wiki.model.Directory;

import java.util.List;
import java.util.function.Function;

public interface DirectoryStore {

    void saveDirectory(Directory directory);

    <T> T readDirectory(Long id,
                        Function<Directory, T> mapper);

    List<Directory> readRootDirectories();
}
