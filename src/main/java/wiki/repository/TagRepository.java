package wiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wiki.model.Tag;
import wiki.model.User;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByNameAndOwner(String name, User owner);

    List<Tag> findByOwner(User owner);
}
