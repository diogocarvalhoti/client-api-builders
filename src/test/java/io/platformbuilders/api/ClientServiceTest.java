package io.platformbuilders.api;

import io.platformbuilders.api.dto.ClientDTO;
import io.platformbuilders.api.model.Client;
import io.platformbuilders.api.service.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

/**
 * Teste para ClientService.
 *
 * @author diogo.matos@meta.com.br
 */
class ClientServiceTest implements ITest {

	@MockBean
	ClientService service;

	/**
	 *
	 */
	@DisplayName("Test Mock helloService + helloRepository")
	@Test
	void findById() {
		System.out.println("teste");

		Client client = new Client();
		client.setId(1L);
//		Mockito.when(repository.findById(1L)).thenReturn(java.util.Optional.of(client));

		Optional<ClientDTO> optional = service.findById(1L);

		Assertions.assertFalse(optional.isPresent());
		//		assertEquals(client.getId(), optional.get().getId());

	}

}
