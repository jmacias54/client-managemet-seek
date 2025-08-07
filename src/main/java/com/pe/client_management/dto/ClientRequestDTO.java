package com.pe.client_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;


@Schema(description = "DTO para la creación de un nuevo cliente")
public record ClientRequestDTO(
	@Schema(
		description = "Nombre del cliente",
		example = "Juan",
		requiredMode = Schema.RequiredMode.REQUIRED
	)
	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
	String firstName,

	@Schema(
		description = "Apellido del cliente",
		example = "Pérez",
		requiredMode = Schema.RequiredMode.REQUIRED
	)
	@NotBlank(message = "El apellido es obligatorio")
	@Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
	String lastName,

	@Schema(
		description = "Edad del cliente en años",
		example = "30",
		requiredMode = Schema.RequiredMode.REQUIRED,
		minimum = "18",
		maximum = "120"
	)
	@NotNull(message = "La edad es obligatoria")
	@Min(value = 18, message = "El cliente debe tener al menos 18 años")
	@Max(value = 120, message = "La edad no puede ser mayor a 120 años")
	Integer age,

	@Schema(
		description = "Fecha de nacimiento del cliente",
		example = "1993-05-15",
		requiredMode = Schema.RequiredMode.REQUIRED
	)
	@NotNull(message = "La fecha de nacimiento es obligatoria")
	@Past(message = "La fecha de nacimiento debe ser en el pasado")
	LocalDate birthDate
) {}