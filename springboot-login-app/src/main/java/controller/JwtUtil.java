package controller;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public interface JwtUtil {

	String generateToken(User user);

}
