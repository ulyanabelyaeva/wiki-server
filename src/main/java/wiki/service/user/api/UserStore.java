package wiki.service.user.api;

import wiki.model.User;

import java.util.Optional;
import java.util.function.Function;

public interface UserStore {

    void saveUser(User user);

    User readUser(String login);

    Optional<User> readUserOptional(String login);

    <T> T readUser(Long id,
                   Function<User, T> mapper);
}
