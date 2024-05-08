package wiki.service.user.api;

import wiki.dto.user.UserDto;
import wiki.model.User;

public interface UserMapper {

    UserDto toDto(User user);
}
