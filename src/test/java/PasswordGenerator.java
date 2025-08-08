import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGenerator {
	public static void main(String[] args) {
		String rawPassword = "password123";
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(rawPassword);
		System.out.println("Hash para '" + rawPassword + "': " + encodedPassword);
	}
}
