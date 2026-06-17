package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Jeu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class ExemplaireRepositoryTest {

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private JeuRepository jeuRepository;

    @Test
    public void testInsertExemplaire() {
        //Arrange
        Jeu domino = jeuRepository.findByTitre("Domino").orElseThrow();

        Exemplaire exDomino = new Exemplaire();
        exDomino.setCodebarre("0000000000001");
        exDomino.setEstLouable(false);
        exDomino.setJeu(domino);

        //Act
        Exemplaire ex = exemplaireRepository.save(exDomino);

        //Assert
        assertThat(ex.getId()).isNotNull();
    }
}
