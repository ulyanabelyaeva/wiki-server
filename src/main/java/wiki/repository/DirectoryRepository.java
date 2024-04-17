package wiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wiki.model.Directory;

import java.util.List;

public interface DirectoryRepository extends JpaRepository<Directory, Long> {

    //todo + фильтрация по пользователю
    List<Directory> findByParentDirectoryIsNull();
}
