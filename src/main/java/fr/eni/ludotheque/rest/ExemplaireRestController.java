package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ExemplaireService;
import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.exception.DataNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exemplaires")
public class ExemplaireRestController {

    ExemplaireService exemplaireService;

    public ExemplaireRestController(ExemplaireService exemplaireService) {
        this.exemplaireService = exemplaireService;
    }

    //GET
    @GetMapping("/{codebarre}")
    public ResponseEntity<ApiResponse<Exemplaire>> trouverExemplaireParCodebarre(@PathVariable String codebarre){
        Exemplaire exemplaire = null;
        try {
            exemplaire = exemplaireService.trouverExemplaireParCodeBarre(codebarre);
        } catch (DataNotFound dtf) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, "Exemplaire : codebarre " + codebarre + " non trouvé", null));
        }

        return ResponseEntity.ok(new ApiResponse(true, "ok", exemplaire));
    }

}
