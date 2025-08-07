package com.pe.client_management.mapper;

import com.pe.client_management.contract.Mapper;
import com.pe.client_management.dto.ClientResponseDTO;
import com.pe.client_management.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientToClientResponseDTOMapper implements Mapper<Client, ClientResponseDTO> {
	@Override
	public ClientResponseDTO map(Client input) {
		return new ClientResponseDTO(input.getId(), input.getFirstName(), input.getLastName(), input.getAge(), input.getBirthDate(), null);
	}
}
