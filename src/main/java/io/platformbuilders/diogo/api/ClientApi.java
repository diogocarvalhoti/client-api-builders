package io.platformbuilders.diogo.api;

import io.platformbuilders.diogo.model.Client;
import io.platformbuilders.diogo.repository.ClientRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api("Clientes")
@RestController
@RequestMapping("/clientes")
public class ClientApi {

    @Autowired
    private ClientRepository clientRepository;

    @Operation(description = "Recurso para consultar Clientes", summary = "Consultar clientes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))),
                    @ApiResponse(responseCode = "204", description = "Not Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("")
    public ResponseEntity<List<Client>> getAll(@Parameter(description = "Nome do cliente", required = false) @RequestParam(required = false) String name) {
        try {
            List<Client> clients = new ArrayList<>();

            if (name == null) {
                clientRepository.findAll().forEach(clients::add);
            } else {
                clientRepository.findByNameContainingIgnoreCase(name, Pageable.unpaged()).forEach(clients::add);
            }
            if (clients.isEmpty()) {
                return new ResponseEntity<>(clients, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(description = "Recurso para consultar Clientes paginado", summary = "Consultar clientes paginado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))),
                    @ApiResponse(responseCode = "204", description = "Not Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("/paginated")
    public ResponseEntity<List<Client>> getAllPaginated(@Parameter(description = "Nome do cliente", required = false) @RequestParam(required = false) String name,
                                                        @Parameter(description = "Página", required = true) @RequestParam(value = "pagina", defaultValue = "0") int page,
                                                        @Parameter(description = "Quantidade por página", required = true) @RequestParam(value = "quantidade", defaultValue = "10") int size) {
        try {
            List<Client> clients = new ArrayList<>();
            PageRequest pageRequest = PageRequest.of(page, size);
            if (name == null) {
                clientRepository.findAll(pageRequest).forEach(clients::add);
            } else {
                clientRepository.findByNameContainingIgnoreCase(name, pageRequest).forEach(clients::add);
            }
            if (clients.isEmpty()) {
                return new ResponseEntity<>(clients, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Operation(description = "Recurso para consultar Cliente por ID", summary = "Consultar cliente por id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))),
                    @ApiResponse(responseCode = "204", description = "Not Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@Parameter(description = "ID do cliente", required = true) @PathVariable("id") long id) {
        Optional<Client> optional = clientRepository.findById(id);

        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Recurso para cadastrar Cliente", summary = "Cadastrar cliente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))),
                    @ApiResponse(responseCode = "204", description = "Not Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PostMapping("")
    public ResponseEntity<Client> create(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Cliente que será cadastrado", required = true, content = @Content(schema = @Schema(implementation = Client.class))) @RequestBody Client client) {
        try {
            Client _client = clientRepository
                    .save(new Client(client.getName()));
            return new ResponseEntity<>(_client, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(description = "Recurso para editar Cliente", summary = "Editar cliente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))),
                    @ApiResponse(responseCode = "204", description = "Not Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@Parameter(description = "ID do cliente", required = true) @PathVariable("id") long id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Cliente que será editado", required = true, content = @Content(schema = @Schema(implementation = Client.class))) @RequestBody Client client) {
        Optional<Client> optional = clientRepository.findById(id);

        if (optional.isPresent()) {
            Client _client = optional.get();
            _client.setName(client.getName());
            return new ResponseEntity<>(clientRepository.save(_client), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Recurso para deletar Cliente por ID", summary = "Deletar cliente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HttpStatus.class))),
                    @ApiResponse(responseCode = "204", description = "Not Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@Parameter(description = "ID do cliente", required = true) @PathVariable("id") long id) {
        try {
            clientRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(description = "Recurso para deletar TODOS Cliente", summary = "Deletar clientes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HttpStatus.class))),
                    @ApiResponse(responseCode = "204", description = "Not Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            clientRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(description = "Recurso para consultar Clientes ativos", summary = "Consultar clientes ativos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Client.class))),
                    @ApiResponse(responseCode = "204", description = "Not Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("/active")
    public ResponseEntity<List<Client>> getByAtivo() {
        try {
            List<Client> clients = clientRepository.findByActiveTrue();

            if (clients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
