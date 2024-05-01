package wiki.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.model.User;
import wiki.repository.UserRepository;
import wiki.service.user.api.UserService;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //todo перделать с появлением авторизации
    @Override
    @Transactional(propagation = MANDATORY)
    public User getCurrentUser() {
        return userRepository.findById(1L)
                .orElseThrow(() -> new IllegalStateException("Default user not created"));
    }
}
