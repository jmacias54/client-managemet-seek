package com.pe.client_management.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO con métricas estadísticas sobre los clientes")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientMetricsDTO(
	@Schema(
		description = "Promedio de edad de todos los clientes",
		example = "42.5"
	)
	Double averageAge,

	@Schema(
		description = "Desviación estándar de las edades",
		example = "12.3"
	)
	Double ageStandardDeviation,

	@Schema(
		description = "Número total de clientes registrados",
		example = "150"
	)
	Long totalClients,

	@Schema(
		description = "Edad mínima entre los clientes",
		example = "18",
		requiredMode = Schema.RequiredMode.NOT_REQUIRED
	)
	Integer minAge,

	@Schema(
		description = "Edad máxima entre los clientes",
		example = "85",
		requiredMode = Schema.RequiredMode.NOT_REQUIRED
	)
	Integer maxAge,

	@Schema(
		description = "Mediana de las edades",
		example = "40",
		requiredMode = Schema.RequiredMode.NOT_REQUIRED
	)
	Integer ageMedian
) {
	public ClientMetricsDTO(Double averageAge, Double ageStandardDeviation, Long totalClients) {
		this(averageAge, ageStandardDeviation, totalClients, null, null, null);
	}

	@Schema(
		description = "Promedio de edad formateado",
		example = "42.50 años",
		accessMode = Schema.AccessMode.READ_ONLY
	)
	public String getFormattedAverageAge() {
		return averageAge != null ? String.format("%.2f años", averageAge) : "N/A";
	}

	@Schema(
		description = "Desviación estándar formateada",
		example = "12.30",
		accessMode = Schema.AccessMode.READ_ONLY
	)
	public String getFormattedStandardDeviation() {
		return ageStandardDeviation != null ? String.format("%.2f", ageStandardDeviation) : "N/A";
	}
}