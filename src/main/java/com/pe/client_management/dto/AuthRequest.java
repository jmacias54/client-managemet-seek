package com.pe.client_management.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
	@NotBlank(message = "Username es obligatorio") String username,
	@NotBlank(message = "Password es obligatorio") String password
) {}