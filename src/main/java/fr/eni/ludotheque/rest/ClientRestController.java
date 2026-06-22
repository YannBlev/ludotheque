package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Client;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {this.clientService = clientService;}

    // POST
    @PostMapping
    public ResponseEntity<Client> ajouterClient(@Valid @RequestBody Client client, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Client clientAjoute = clientService.ajouterClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/clients/" + clientAjoute.getId())
                .body(clientAjoute);
    }
}
