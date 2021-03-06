package io.platformbuilders.api.repository;

import io.platformbuilders.api.model.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    List<Client> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    List<Client> findByActiveTrue();
}
