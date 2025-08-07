package com.pe.client_management.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "DTO con la información completa de un cliente")
public record ClientResponseDTO(
	@Schema(
		description = "ID único del cliente",
		example = "1",
		accessMode = Schema.AccessMode.READ_ONLY
	)
	Long id,

	@Schema(
		description = "Nombre del cliente",
		example = "Juan"
	)
	String firstName,

	@Schema(
		description = "Apellido del cliente",
		example = "Pérez"
	)
	String lastName,

	@Schema(
		description = "Edad actual del cliente en años",
		example = "30"
	)
	Integer age,

	@Schema(
		description = "Fecha de nacimiento del cliente",
		example = "1993-05-15"
	)
	LocalDate birthDate,

	@Schema(
		description = "Fecha estimada de jubilación (calculada como birthDate + 65 años)",
		example = "2058-05-15",
		accessMode = Schema.AccessMode.READ_ONLY
	)
	LocalDate estimatedEventDate
) {}