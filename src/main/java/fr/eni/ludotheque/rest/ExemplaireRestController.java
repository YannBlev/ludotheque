package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ExemplaireService;
import fr.eni.ludotheque.bo.Exemplaire;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exemplaires")
public class ExemplaireRestController {

    ExemplaireService exemplaireService;

    public ExemplaireRestController(ExemplaireService exemplaireService) {
        this.exemplaireService = exemplaireService;
    }

    //GET
    @GetMapping("/{codebarre}")
    public ResponseEntity<?> trouverExemplaireParCodebarre(@PathVariable String codebarre){
            Exemplaire exemplaire = exemplaireService.trouverExemplaireParCodeBarre(codebarre);
            return ResponseEntity.ok(exemplaire);
    }

}
