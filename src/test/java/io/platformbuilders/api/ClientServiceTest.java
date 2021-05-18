package io.platformbuilders.api;

import io.platformbuilders.api.dto.ClientDTO;
import io.platformbuilders.api.model.Client;
import io.platformbuilders.api.service.ClientService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

/**
 * Teste para ClientService.
 *
 * @author diogo.matos@meta.com.br
 */
public class ClientServiceTest implements ITest{

	@MockBean
	ClientService service;

	@Before
	public void initMocks() {
	}
	/**
	 * 
	 */
	@DisplayName("Test Mock helloService + helloRepository")
	@Test
	public void buscarPorId() {
		System.out.println("teste");

		Client client = new Client();
		client.setId(1L);
//		Mockito.when(repository.findById(1L)).thenReturn(java.util.Optional.of(client));

		Optional<ClientDTO> optional = service.findById(1L);

		Assertions.assertFalse(optional.isPresent());
		//		assertEquals(client.getId(), optional.get().getId());

	}

}
