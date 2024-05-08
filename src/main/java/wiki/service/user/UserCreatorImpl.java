package wiki.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.user.NewUserDto;
import wiki.model.User;
import wiki.service.user.api.UserCreator;
import wiki.service.user.api.UserStore;

import java.util.Optional;

@Service
public class UserCreatorImpl implements UserCreator {

    private final PasswordEncoder passwordEncoder;
    private final UserStore userStore;

    public UserCreatorImpl(PasswordEncoder passwordEncoder,
                           UserStore userStore) {
        this.passwordEncoder = passwordEncoder;
        this.userStore = userStore;
    }

    @Override
    @Transactional
    public Long create(NewUserDto request) {
        Optional<User> existedUser = userStore.readUserOptional(request.getLogin());
        if (existedUser.isPresent()){
            throw new IllegalArgumentException("User already exists");
        }

        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userStore.saveUser(user);
        return user.getId();
    }
}
