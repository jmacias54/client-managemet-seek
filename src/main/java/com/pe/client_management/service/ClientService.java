package com.pe.client_management.service;

import com.pe.client_management.dto.ClientMetricsDTO;
import com.pe.client_management.dto.ClientRequestDTO;
import com.pe.client_management.dto.ClientResponseDTO;
import com.pe.client_management.entity.Client;

import java.util.List;

public interface ClientService {
	Client createClient(ClientRequestDTO request);

	ClientMetricsDTO getClientMetrics();

	List<ClientResponseDTO> getAllClients();
}
