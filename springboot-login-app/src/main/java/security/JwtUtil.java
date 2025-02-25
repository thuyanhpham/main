package security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Component;


@Component
public class JwtUtil {

	private static final String SECRET_KEY = "mySecretKeymySecretKeymySecretKeymySecretKey";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) 
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Hết hạn sau 1 giờ
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  
                .compact();
    }
    
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) 
                .parseClaimsJws(token)
                .getBody();
    }
    
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired1(token);
    }

	private boolean isTokenExpired1(String token) {
		return false;
	}
	
	 @SuppressWarnings("unused")
	private boolean isTokenExpired(final String token) {
	        return extractClaims(token).getExpiration().before(new Date());
	    }
}

