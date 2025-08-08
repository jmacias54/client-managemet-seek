package com.pe.client_management;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PasswordValidationTest {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void testPasswordMatch() {
		String rawPassword = "password123";
		String encodedPassword = "$2a$10$N9qo8uLOickgx2ZMRZoMy.Mrq4H6z3X7l4zJY1J13v6VtV1QO/YG2";

		assertTrue(passwordEncoder.matches(rawPassword, encodedPassword),
			"La contraseña no coincide con el hash almacenado");
	}
}