package wiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wiki.model.Page;
import wiki.model.User;

import java.util.List;

public interface PageRepository extends JpaRepository<Page, Long> {

    List<Page> findByDirectoryIsNullAndOwner(User owner);
}
