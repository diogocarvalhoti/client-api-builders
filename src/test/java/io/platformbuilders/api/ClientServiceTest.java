package io.platformbuilders.api;

import io.platformbuilders.api.dto.ClientDTO;
import io.platformbuilders.api.model.Client;
import io.platformbuilders.api.repository.ClientRepository;
import io.platformbuilders.api.service.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Teste para PerfilService.
 *
 * @author diogo.matos@meta.com.br
 */

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

     
	@InjectMocks
	private ClientService service;

	@Mock
	private ClientRepository repository;

	/**
	 * 
	 */
	@Before
	public void init() {
	}
	
	@Test
	public void buscarPorId() {

		Client client = new Client();
		client.setId(1L);
		Mockito.when(repository.findById(1L)).thenReturn(java.util.Optional.of(client));

		Optional<ClientDTO> optional = service.findById(1L);

		assertTrue(optional.isPresent());
		assertEquals(client.getId(), optional.get().getId());
		
	}

}
