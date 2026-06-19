package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.bo.dto.JeuNbExemplaireDisponibleDto;
import fr.eni.ludotheque.dal.GenreRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
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
    public void testEstDisponible() {
        //Arrange
        Jeu jeu = jeuRepository.findByTitre("Domino").orElseThrow();

        // Act
        boolean jeuDisponible = jeuService.estDisponible(jeu);

        //Assert
        assertThat(jeuDisponible).isTrue();
    }

    @Test
    void testListJeuxDisponiblesToutGenre() {

        // Arrange + Act
        List<JeuNbExemplaireDisponibleDto> result =
                jeuService.listeJeuxDisponibles(null);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(8);

        JeuNbExemplaireDisponibleDto domino = result.stream()
                .filter(j -> "Domino".equals(j.getTitre()))
                .findFirst()
                .orElseThrow();

        assertThat(domino.getNbExemplaire()).isEqualTo(1L);

        JeuNbExemplaireDisponibleDto echec = result.stream()
                .filter(j -> "Echec".equals(j.getTitre()))
                .findFirst()
                .orElseThrow();

        assertThat(echec.getNbExemplaire()).isEqualTo(5L);

        JeuNbExemplaireDisponibleDto magic = result.stream()
                .filter(j -> "Magic: The Gathering".equals(j.getTitre()))
                .findFirst()
                .orElseThrow();

        assertThat(magic.getNbExemplaire()).isEqualTo(2L);
    }

    @Test
    void testListJeuxDisponiblesFiltreGenre() {

        // Arrange + Act
        List<JeuNbExemplaireDisponibleDto> result =
                jeuService.listeJeuxDisponibles(2L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(3);

        JeuNbExemplaireDisponibleDto domino = result.stream()
                .filter(j -> "Domino".equals(j.getTitre()))
                .findFirst()
                .orElseThrow();

        assertThat(domino.getNbExemplaire()).isEqualTo(1L);

        JeuNbExemplaireDisponibleDto echec = result.stream()
                .filter(j -> "Echec".equals(j.getTitre()))
                .findFirst()
                .orElseThrow();

        assertThat(echec.getNbExemplaire()).isEqualTo(5L);

        JeuNbExemplaireDisponibleDto magic = result.stream()
                .filter(j -> "Magic: The Gathering".equals(j.getTitre()))
                .findFirst()
                .orElseThrow();

        assertThat(magic.getNbExemplaire()).isEqualTo(2L);
    }
}
