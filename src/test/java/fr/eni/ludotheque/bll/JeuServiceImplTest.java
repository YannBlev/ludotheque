package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.dal.GenreRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Slf4j
public class JeuServiceImplTest {

    @Autowired
    JeuService jeuService;

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
        jeuService.ajouterJeu(jeu);

        //Assert
        Jeu jeuBD = jeuRepository.findByTitre("Puzzle").orElseThrow();
        System.out.println("******************************************");
        System.out.println(jeuRepository.findByTitre("Puzzle"));
        System.out.println("******************************************");
        assertThat(jeuBD).isNotNull();
    }

    @Test
    @DisplayName("Test trouver les jeux et le nb d'exemplaires disponible")
    public void testTrouverJeuxDisponibles() {

        List<Jeu> jeux = jeuService.listeJeuxCatalogue("TOUS");
        System.out.println("------------------------------------------------------------------");
        jeux.forEach(System.out::println);
        System.out.println("------------------------------------------------------------------");
        log.info(jeux.toString());
    }

}
