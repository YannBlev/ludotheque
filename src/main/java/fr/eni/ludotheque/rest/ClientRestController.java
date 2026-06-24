package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.dto.AdresseDTO;
import fr.eni.ludotheque.bo.dto.ClientDTO;
import fr.eni.ludotheque.exception.ClientNotFoundException;
import fr.eni.ludotheque.exception.EmailClientAlreadyExistException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {this.clientService = clientService;}

    // POST
    @PostMapping
    public ResponseEntity<ApiResponse<Client>> ajouterClient(@Valid @RequestBody ClientDTO client, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Client nouveauClient = null;
        try {
            nouveauClient = clientService.ajouterClient(client);
        } catch (EmailClientAlreadyExistException e) {
            ApiResponse<Client> apiResponse = new ApiResponse(false, "erreur de validation: email existe déjà.", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
        ApiResponse<Client> apiResponse = new ApiResponse(true, "ok", nouveauClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    //GET
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<ApiResponse<List<Client>>> trouverClientParNom(@RequestParam(required = false, defaultValue = "") String filtreNom) {
        List<Client> clientList = clientService.trouverClientsParNom(filtreNom);
        String message = clientList.size()>0?"ok":"aucun client trouvé";

        return ResponseEntity.ok(new ApiResponse(true, message, clientList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Client>> trouverClientParId(@PathVariable Long id) {
        Client client = null;
        try {
            client = clientService.trouverClientParId(id);
        } catch (ClientNotFoundException cnf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, cnf.getMessage(), null));
        }

        return ResponseEntity.ok(new ApiResponse<>(true, "ok", client));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> supprimerClient(@PathVariable Long id) {
        try {
            clientService.supprimerClient(id);
        } catch (ClientNotFoundException cnf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "Client : " + id + " non trouvé", null));
        }
        ApiResponse<String> apiResponse = new ApiResponse(true, "ok", null);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Client>> modifierClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDto, BindingResult result) {
        Client client = null;
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            client = clientService.modifierClient(id, clientDto);
        } catch (ClientNotFoundException cnf) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ApiResponse<Client> apiResponse = new ApiResponse(true, "ok", client);
        return ResponseEntity.ok(apiResponse);
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



}
