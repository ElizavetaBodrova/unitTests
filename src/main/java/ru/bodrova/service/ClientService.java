package ru.bodrova.service;

import org.springframework.stereotype.Service;
import ru.bodrova.model.Client;
import ru.bodrova.repository.ClientRepository;

import java.math.BigDecimal;
import java.sql.SQLException;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Long createAccount(Client client) {
        try{
            return (long) clientRepository.createClient(client);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public BigDecimal getBalance(Long id){
        try{
            return clientRepository.getClient(id).getBalance();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public Client getClientByFio(String fio){
        try{
            return clientRepository.getClient(fio);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
