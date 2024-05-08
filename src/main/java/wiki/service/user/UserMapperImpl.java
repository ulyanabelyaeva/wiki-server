package wiki.service.user;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.user.UserDto;
import wiki.model.User;
import wiki.service.user.api.UserMapper;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Component
@Transactional(propagation = MANDATORY)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(String.valueOf(user.getId()));
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
