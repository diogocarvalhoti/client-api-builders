package io.platformbuilders.api.resource;

import io.platformbuilders.api.dto.ClientDTO;
import io.platformbuilders.api.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api("Clientes")
@RestController
@RequestMapping("/clients")
public class ClientResource {

    @Autowired
    private ClientService service;

    @Operation(description = "Recurso para consultar Clientes", summary = "Consultar clientes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDTO.class))),
                    @ApiResponse(responseCode = "204", description = "Not Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("")
    public ResponseEntity<List<ClientDTO>> getAll(@Parameter(description = "Nome do cliente", required = false) @RequestParam(required = false) String name) {
        try {
            List<ClientDTO> dtos = this.service.findAll(name);

            if (dtos.isEmpty()) {
                return new ResponseEntity<>(dtos, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(description = "Recurso para consultar Clientes paginado", summary = "Consultar clientes paginado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDTO.class))),
                    @ApiResponse(responseCode = "204", description = "Not Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("/paginated")
    public ResponseEntity<List<ClientDTO>> getAllPaginated(@Parameter(description = "Nome do cliente", required = false) @RequestParam(required = false) String name,
                                                           @Parameter(description = "Página", required = true) @RequestParam(value = "pagina", defaultValue = "0") int page,
                                                           @Parameter(description = "Quantidade por página", required = true) @RequestParam(value = "quantidade", defaultValue = "10") int size) {
        try {
            List<ClientDTO> dtos = this.service.findAllPaginated(name, page, size);

            if (dtos.isEmpty()) {
                return new ResponseEntity<>(dtos, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Operation(description = "Recurso para consultar Cliente por ID", summary = "Consultar cliente por id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDTO.class))),
                    @ApiResponse(responseCode = "204", description = "Not Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getById(@Parameter(description = "ID do cliente", required = true) @PathVariable("id") long id) {
        Optional<ClientDTO> optional = this.service.findById(id);

        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(description = "Recurso para cadastrar Cliente", summary = "Cadastrar cliente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDTO.class))),
                    @ApiResponse(responseCode = "204", description = "Not Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PostMapping("")
    public ResponseEntity<ClientDTO> create(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Cliente que será cadastrado", required = true, content = @Content(schema = @Schema(implementation = ClientDTO.class))) @RequestBody ClientDTO dto) {
        try {
            ClientDTO _dto = this.service.save(dto);
            return new ResponseEntity<>(_dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(description = "Recurso para editar Cliente", summary = "Editar cliente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDTO.class))),
                    @ApiResponse(responseCode = "204", description = "Not Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@Parameter(description = "ID do cliente", required = true) @PathVariable("id") long id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Cliente que será editado", required = true, content = @Content(schema = @Schema(implementation = ClientDTO.class))) @RequestBody ClientDTO dto) {
        Optional<ClientDTO> optional = this.service.findById(id);

        if (optional.isPresent()) {
            ClientDTO _dto = optional.get();
            _dto.setName(dto.getName());
            _dto.setActive(dto.getActive());
            return new ResponseEntity<>(this.service.save(_dto), HttpStatus.OK);
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
            this.service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
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
            this.service.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(description = "Recurso para consultar Clientes ativos", summary = "Consultar clientes ativos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientDTO.class))),
                    @ApiResponse(responseCode = "204", description = "Not Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("/active")
    public ResponseEntity<List<ClientDTO>> getByAtivo() {
        try {
            List<ClientDTO> dtos = this.service.findByActiveTrue();

            if (dtos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
