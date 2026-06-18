package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.dal.GenreRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class JeuServiceImplTest {

    @Autowired
    JeuRepository jeuRepository;

    @Autowired
    GenreRepository genreRepository;

    @Test
    public void testAjouterJeu() {
        //Arrange
        Genre plateau = genreRepository.findByLibelle("plateau").orElseThrow();
        Genre famille = genreRepository.findByLibelle("famille").orElseThrow();

        Jeu jeu = new Jeu();
        jeu.setTitre("Puzzle");
        jeu.setReference("PUZ-001");
        jeu.setDescription("Est-ce vraiment un jeu ?");
        jeu.setDuree(90);
        jeu.setTarifJour(3.5f);

        jeu.addGenre(plateau);
        jeu.addGenre(famille);

        //Act
        jeuRepository.save(jeu);

        //Assert
        Jeu jeuBD = jeuRepository.findByTitre("Puzzle").orElseThrow();
        System.out.println("******************************************");
        System.out.println(jeuRepository.findByTitre("Puzzle"));
        System.out.println("******************************************");
        assertThat(jeuBD).isNotNull();
    }
}
