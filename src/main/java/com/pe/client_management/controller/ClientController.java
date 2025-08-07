package com.pe.client_management.controller;

import com.pe.client_management.contract.Mapper;
import com.pe.client_management.dto.ClientMetricsDTO;
import com.pe.client_management.dto.ClientRequestDTO;
import com.pe.client_management.dto.ClientResponseDTO;
import com.pe.client_management.entity.Client;
import com.pe.client_management.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
	private final ClientService clientService;
	private final Mapper<Client, ClientResponseDTO> clientToClientResponseDTOMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ClientResponseDTO>  createClient(@Valid @RequestBody ClientRequestDTO request) {
		return ResponseEntity.ok(this.clientToClientResponseDTOMapper.map(this.clientService.createClient(request)));
	}

	@GetMapping("/metrics")
	public ResponseEntity<ClientMetricsDTO>   getMetrics() {
		return ResponseEntity.ok(this.clientService.getClientMetrics());
	}

	@GetMapping
	public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
		return ResponseEntity.ok(this.clientService.getAllClients());
	}
}