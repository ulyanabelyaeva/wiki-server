package wiki.controller;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.dto.user.AuthDto;
import wiki.dto.user.NewUserDto;
import wiki.dto.user.UserDto;
import wiki.service.user.api.AuthService;
import wiki.service.user.api.UserCreator;
import wiki.service.user.api.UserMapper;
import wiki.service.user.api.UserStore;

import javax.validation.Valid;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = getLogger(UserController.class);

    private final AuthService authService;
    private final UserCreator userCreator;
    private final UserMapper userMapper;
    private final UserStore userStore;

    public UserController(AuthService authService,
                          UserCreator userCreator,
                          UserMapper userMapper,
                          UserStore userStore) {
        this.authService = authService;
        this.userCreator = userCreator;
        this.userMapper = userMapper;
        this.userStore = userStore;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(
            @RequestBody
            @Valid
            NewUserDto request
    ) {
        LOGGER.info("Create USER requested: {}", request);
        Long id = userCreator.create(request);

        UserDto response = userStore.readUser(id, userMapper::toDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody
            @Valid
            AuthDto request) {
        LOGGER.info("LOGIN requested: {}", request);
        String response = authService.createToken(request);
        return ResponseEntity.ok(response);
    }
}
