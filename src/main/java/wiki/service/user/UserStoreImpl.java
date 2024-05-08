package wiki.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.model.User;
import wiki.repository.UserRepository;
import wiki.service.user.api.UserStore;

import java.util.Optional;
import java.util.function.Function;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Service
public class UserStoreImpl implements UserStore {

    private final UserRepository userRepository;

    public UserStoreImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public User readUser(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("User " + login + " isn`t founded"));
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public Optional<User> readUserOptional(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public <T> T readUser(Long id,
                          Function<User, T> mapper) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User " + id + " isn`t founded"));
        return mapper.apply(user);
    }
}
