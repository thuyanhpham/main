package controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot_login_app.springboot_login_app.repository.userRepository;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	//API đăng ký
	
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		@SuppressWarnings("unused")
		User save = new User();
		return "Đăng ký thành công!";
			
		}
	
	//API đăng nhập
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		Optional<User> dbUser = userRepository.findByUsername(user.getName());
		
		if (dbUser.isPresent() && passwordEncoder.matches(user.getPassword(), dbUser.get().getPassword())) {
			
			return jwtUtil.generateToken(user);
		} else {
			return "Sai tài khoản hoặc mật khẩu!";
		}
	}
}
