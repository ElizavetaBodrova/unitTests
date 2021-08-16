package ru.bodrova.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import ru.bodrova.model.Client;
import ru.bodrova.service.ClientService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value="/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createAccount(@RequestBody Client client) {
        return clientService.createAccount(client);
    }

    @GetMapping("/balance/{id}")
    public BigDecimal getBalance(@PathVariable String id) {
        return clientService.getBalance(Long.parseLong(id));
    }

    @PostMapping(value="/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public Client getClientByFio(@RequestParam("fio") String fio) {
        return clientService.getClientByFio(fio);
    }

}
