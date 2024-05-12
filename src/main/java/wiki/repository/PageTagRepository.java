package wiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wiki.model.Page;
import wiki.model.PageTag;

import java.util.List;

public interface PageTagRepository extends JpaRepository<PageTag, Long> {

    List<PageTag> findByPage(Page page);
}
