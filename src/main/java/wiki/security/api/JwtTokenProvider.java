package wiki.security.api;

import org.springframework.security.core.Authentication;

public interface JwtTokenProvider {

    String createToken(String username);

    Authentication getAuthentication(String token);
}
