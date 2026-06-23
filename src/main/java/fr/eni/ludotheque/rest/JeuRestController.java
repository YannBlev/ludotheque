package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.JeuService;
import fr.eni.ludotheque.bo.Jeu;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jeux")
public class JeuRestController {

    private final JeuService jeuService;

    public JeuRestController(JeuService jeuService) {
        this.jeuService = jeuService;
    }

    // GET
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Jeu> trouverJeuxCatalogueParNom(@RequestParam String titre) {
        if (titre.isEmpty()) {
            titre = "TOUS";
        }
        return jeuService.listeJeuxCatalogue(titre);
    }

}
