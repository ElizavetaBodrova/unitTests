package ru.bodrova.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bodrova.model.Client;

public interface ClientRepository extends CrudRepository<Client,Long> {
    Client findClientByFio(String fio);
    Client findClientById(Long id);

}
