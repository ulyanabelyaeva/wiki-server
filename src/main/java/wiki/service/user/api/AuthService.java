package wiki.service.user.api;

import wiki.dto.user.AuthDto;
import wiki.model.User;

public interface AuthService {

    User getCurrentUser();

    String createToken(AuthDto request);
}
