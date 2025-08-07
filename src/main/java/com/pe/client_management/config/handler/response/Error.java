package com.pe.client_management.config.handler.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "title", "detail"})
public class Error {

	@JsonProperty("code")
	private String code;

	@JsonProperty("title")
	private String title;

	@JsonProperty("detail")
	private String detail;


}
