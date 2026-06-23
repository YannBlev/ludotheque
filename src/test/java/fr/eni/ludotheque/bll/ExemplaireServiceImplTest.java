package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import fr.eni.ludotheque.exception.DataNotFound;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class ExemplaireServiceImplTest {
    @Autowired
    private ExemplaireService exemplaireService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Test
    @DisplayName("Trouver un exemplaire par codebarre cas codebarre est inconnu-doit renvoyer une exception ")
    public void testTrouverExemplaireParCodebarreCasCodebarreInconnu() {
        //Arrange
        String codebarre = "9999999999999";
        //when(exemplaireRepository.findByCodebarre(codebarre)).thenReturn(Optional.empty());

        //Act + Assert
        assertThrows(DataNotFound.class, ()->exemplaireService.trouverExemplaireParCodeBarre(codebarre));

    }

    @Test
    @DisplayName("Trouver un exemplaire par codebarre cas codebarre est inconnu-doit renvoyer une exception ")
    public void testTrouverExemplaireParCodebarreCasCodebarreConnu() {
        //Arrange
        String codebarre = "0000000000001";

        Exemplaire exemplaire = exemplaireService.trouverExemplaireParCodeBarre(codebarre);

        //Act + Assert
        assertThat(exemplaire.getCodebarre()).isEqualTo(codebarre);
        assertThat(exemplaire.getId()).isEqualTo(1);
    }

}
