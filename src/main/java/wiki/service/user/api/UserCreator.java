package wiki.service.user.api;

import wiki.dto.user.NewUserDto;

public interface UserCreator {

    Long create(NewUserDto request);
}
