package security;

import java.util.Date;

public interface Claims {

	Date getExpiration();

	String getSubject();

}
