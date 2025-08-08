package com.pe.client_management.controller;

import com.pe.client_management.dto.AuthRequest;
import com.pe.client_management.dto.AuthResponse;
import com.pe.client_management.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		try {
			// Verificación manual de credenciales
			UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());

			if (!passwordEncoder.matches(request.password(), userDetails.getPassword())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Credenciales incorrectas");
			}

			// Autenticación oficial
			Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
					request.username(),
					request.password()
				)
			);

			// Generación del token
			String token = jwtUtils.generateToken(userDetails);

			return ResponseEntity.ok(new AuthResponse(
				token
			));

		} catch (UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body("Usuario no encontrado");
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body("Credenciales inválidas");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Error en el servidor: " + e.getMessage());
		}
	}
}
