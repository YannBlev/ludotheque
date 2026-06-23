package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.dto.AdresseDTO;
import fr.eni.ludotheque.bo.dto.ClientDTO;
import fr.eni.ludotheque.exception.ClientNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {this.clientService = clientService;}

    // POST
    @PostMapping
    public ResponseEntity<Client> ajouterClient(@Valid @RequestBody ClientDTO client, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Client clientAjoute = clientService.ajouterClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/clients/" + clientAjoute.getId())
                .body(clientAjoute);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerClient(@PathVariable Long id) {
        try {
            clientService.supprimerClient(id);
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } catch (ClientNotFoundException cnf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body( "Client not found");
        }
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Client> modifierClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            Client clientModifie = clientService.modifierClient(id, clientDto);
            return ResponseEntity.status(HttpStatus.OK).body(clientModifie);
        } catch (ClientNotFoundException cnf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // PATCH
    @PatchMapping("/{id}/adresse")
    public ResponseEntity<ApiResponse<Client>> modifierClientAdresse(@PathVariable Long id, @RequestBody AdresseDTO adresseDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            Client adresseClientModifie = clientService.modifierAdresse(id, adresseDto);
            return ResponseEntity.ok(new ApiResponse<>(true, "Adresse modifiée", adresseClientModifie));
        } catch (ClientNotFoundException cnf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, cnf.getMessage(), null));
        }
    }

    //GET
    @GetMapping("/names/{name}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Client> trouverClientParNom(@PathVariable String name) {
        return clientService.trouverClientsParNom(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> trouverClientParId(@PathVariable Long id) {
        Client client = clientService.trouverClientParId(id);
        return ResponseEntity.ok(client);
    }

}
