package io.platformbuilders.api;

import io.platformbuilders.api.dto.ClientDTO;
import io.platformbuilders.api.model.Client;
import io.platformbuilders.api.repository.ClientRepository;
import io.platformbuilders.api.service.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * Teste para PerfilService.
 *
 * @author diogo.matos@meta.com.br
 */
@SpringBootTest
public class ClientServiceTest {

     
	@InjectMocks
	private ClientService service;

	@Mock
	private ClientRepository repository;

	/**
	 * 
	 */
	@Test
	public void buscarPorId() {
		System.out.println("teste");

		Client client = new Client();
		client.setId(1L);
		Mockito.when(repository.findById(1L)).thenReturn(java.util.Optional.of(client));

		Optional<ClientDTO> optional = service.findById(1L);

		Assertions.assertTrue(optional.isPresent());
		//		assertEquals(client.getId(), optional.get().getId());

	}

}
