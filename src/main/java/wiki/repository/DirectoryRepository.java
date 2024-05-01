package wiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wiki.model.Directory;
import wiki.model.User;

import java.util.List;

public interface DirectoryRepository extends JpaRepository<Directory, Long> {

    List<Directory> findByParentDirectoryIsNullAndOwner(User owner);
}
