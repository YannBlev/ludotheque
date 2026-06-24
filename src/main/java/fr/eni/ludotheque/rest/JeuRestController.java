package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.JeuService;
import fr.eni.ludotheque.bo.Jeu;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jeux")
public class JeuRestController {

    private final JeuService jeuService;

    public JeuRestController(JeuService jeuService) {
        this.jeuService = jeuService;
    }

    // GET
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<ApiResponse<List<Jeu>>> trouverJeuxCatalogueParNom(@RequestParam String titre) {
        if (titre.isEmpty() || titre == null || "".equals(titre.trim())) {
            titre = "TOUS";
        }
        List<Jeu> jeux =  jeuService.listeJeuxCatalogue(titre);
        return ResponseEntity.ok(new ApiResponse(true, "ok", jeux));
    }

}
