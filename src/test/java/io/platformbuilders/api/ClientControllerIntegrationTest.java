package io.platformbuilders.api;

import io.platformbuilders.api.dto.ClientDTO;
import io.platformbuilders.api.model.Client;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = ClientApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerIntegrationTest {

    public static final String CLIENT_RESOURCE = "/api/v1/clients/";

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void a_getAll_status_NO_CONTENT() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + CLIENT_RESOURCE,
                HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void create() {
        Client client = new Client("Diogo Carvalho de Matos", Boolean.TRUE);
        ResponseEntity<Client> postResponse = restTemplate.postForEntity(getRootUrl() + CLIENT_RESOURCE, client, Client.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void getAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + CLIENT_RESOURCE,
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void getAllByName_status_OK() {
        String name = "dio";

        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + CLIENT_RESOURCE + "?name=" + name,
                HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void getAllByName_status_NO_CONTENT() {
        String name = "pedro";

        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + CLIENT_RESOURCE + "?name=" + name,
                HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void getAllPaginated_status_OK() {
        String name = "dio";

        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + CLIENT_RESOURCE + "paginated?name=" + name,
                HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void getAllPaginated_status_NO_CONTENT() {
        String name = "pedro";

        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + CLIENT_RESOURCE + "paginated?name=" + name,
                HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void getById() {
        int id = 1;
        ClientDTO dto = restTemplate.getForObject(getRootUrl() + CLIENT_RESOURCE + id, ClientDTO.class);
        assertNotNull(dto);
    }


    @Test
    public void update() {
        int id = 1;
        ClientDTO dto = restTemplate.getForObject(getRootUrl() + CLIENT_RESOURCE + id, ClientDTO.class);
        dto.setName(dto.getName() + " Updated");
        restTemplate.put(getRootUrl() + CLIENT_RESOURCE + id, dto);

        ClientDTO updatedDTO = restTemplate.getForObject(getRootUrl() + CLIENT_RESOURCE + id, ClientDTO.class);
        assertTrue(updatedDTO.getName().endsWith(" Updated"));
        assertNotNull(updatedDTO);
    }

    @Test
    public void z_delete() {
        int id = 1;
        ClientDTO dto = restTemplate.getForObject(getRootUrl() + CLIENT_RESOURCE + id, ClientDTO.class);
        assertNotNull(dto);
        restTemplate.delete(getRootUrl() + CLIENT_RESOURCE + id);
        try {
            dto = restTemplate.getForObject(getRootUrl() + CLIENT_RESOURCE + id, ClientDTO.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
