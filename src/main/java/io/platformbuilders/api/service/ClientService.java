package io.platformbuilders.api.service;

import io.platformbuilders.api.dto.ClientDTO;
import io.platformbuilders.api.mapper.ClientMapper;
import io.platformbuilders.api.model.Client;
import io.platformbuilders.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(Transactional.TxType.NOT_SUPPORTED)
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientMapper mapper;

    public List<ClientDTO> findAll(String name) {
        List<ClientDTO> dtos = new ArrayList<>();

        if (name == null) {
            repository.findAll().forEach(client -> dtos.add(mapper.entityToDTO(client)));
        } else {
            repository.findByNameContainingIgnoreCase(name, Pageable.unpaged())
                    .forEach(client -> dtos.add(mapper.entityToDTO(client)));
        }
        return dtos;
    }

    public List<ClientDTO> findAllPaginated(String name, int page, int size) {
        List<ClientDTO> dtos = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(page, size);
        if (name == null) {
            repository.findAll(pageRequest).forEach(client -> dtos.add(mapper.entityToDTO(client)));
        } else {
            repository.findByNameContainingIgnoreCase(name, pageRequest).forEach(client -> dtos.add(mapper.entityToDTO(client)));
        }
        return dtos;
    }

    public Optional<ClientDTO> findById(Long id) {
        Optional<Client> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            return Optional.of(this.mapper.entityToDTO(optional.get()));
        }
        return Optional.empty();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public ClientDTO save(ClientDTO dto) {
        Client client = this.repository.save(this.mapper.dtoToEntity(dto));
        return this.mapper.entityToDTO(client);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteById(long id) {
        this.repository.deleteById(id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteAll() {
        this.repository.deleteAll();
    }

    public List<ClientDTO> findByActiveTrue() {
        List<ClientDTO> dtos = new ArrayList<>();
        this.repository.findByActiveTrue().forEach(client -> dtos.add(mapper.entityToDTO(client)));
        return dtos;
    }
}
