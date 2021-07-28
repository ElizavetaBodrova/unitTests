package ru.bodrova.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ru.bodrova.model.Client;
import ru.bodrova.service.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long createAccount(@RequestParam Client client) {
        return ;
    }

    @GetMapping("/balance/{id}")
    @ResponseBody
    public Long getBalance(@PathVariable String id) {
        return ;
    }

    @RequestMapping(value="/find/{fio}", method=RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Client getClientByFio(@PathVariable String fio) {
        return ;
    }

}
