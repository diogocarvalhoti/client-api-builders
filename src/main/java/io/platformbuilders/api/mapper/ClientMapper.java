package io.platformbuilders.api.mapper;

import io.platformbuilders.api.dto.ClientDTO;
import io.platformbuilders.api.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientMapper extends AbstractMapper<Client, ClientDTO> {

    @Autowired
    private Converter converter;

    public ClientMapper() {
        super(Client.class, ClientDTO.class);
    }

    @Override
    public ClientDTO entityToDTO(Client entity) {
        return converter.converter(entity, ClientDTO.class);
    }

    @Override
    public Client dtoToEntity(ClientDTO dto) {
        return converter.converter(dto, Client.class);
    }

    public List<ClientDTO> listEntityToDTO(List<Client> listPessoa) {
        List<ClientDTO> list = new ArrayList<>();
        listPessoa.forEach(pessoa -> {
            list.add(entityToDTO(pessoa));
        });

        return list;
    }

}
