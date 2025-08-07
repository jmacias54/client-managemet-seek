package com.pe.client_management.service.impl;

import com.pe.client_management.dto.ClientMetricsDTO;
import com.pe.client_management.dto.ClientRequestDTO;
import com.pe.client_management.dto.ClientResponseDTO;
import com.pe.client_management.entity.Client;
import com.pe.client_management.repository.ClientRepository;
import com.pe.client_management.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;

	public Client createClient(ClientRequestDTO request) {
		Client client = new Client();
		client.setFirstName(request.firstName());
		client.setLastName(request.lastName());
		client.setAge(request.age());
		client.setBirthDate(request.birthDate());
		client.setCreatedAt(LocalDateTime.now());
		return clientRepository.save(client);
	}

	public ClientMetricsDTO getClientMetrics() {
		return new ClientMetricsDTO(
			clientRepository.findAverageAge(),
			clientRepository.findAgeStandardDeviation(),
			clientRepository.count()
		);
	}

	public List<ClientResponseDTO> getAllClients() {
		return clientRepository.findAll().stream()
			.map(this::mapToResponseDTO)
			.collect(Collectors.toList());
	}

	private ClientResponseDTO mapToResponseDTO(Client client) {
		LocalDate estimatedEventDate = calculateEstimatedEventDate(client);
		return new ClientResponseDTO(
			client.getId(),
			client.getFirstName(),
			client.getLastName(),
			client.getAge(),
			client.getBirthDate(),
			estimatedEventDate
		);
	}

	private LocalDate calculateEstimatedEventDate(Client client) {

		return client.getBirthDate().plusYears(65);
	}
}
