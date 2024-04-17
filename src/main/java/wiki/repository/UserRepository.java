package wiki.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wiki.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
