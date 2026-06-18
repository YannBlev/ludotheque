package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JeuRepositoryTest {

    @Autowired
    private JeuRepository jeuRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void testInsertionJeuAvecGenres() {

        // Arrange
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

        // Act
        jeuRepository.save(jeu);

        // Assert
        Jeu jeuBD = jeuRepository.findById(jeu.getId()).orElseThrow();

        assertThat(jeuBD.getTitre()).isEqualTo("Puzzle");
        assertThat(jeuBD.getReference()).isEqualTo("PUZ-001");
        assertThat(jeuBD.getGenres()).hasSize(2);
        assertThat(jeuBD.getGenres()).extracting(Genre::getLibelle).containsExactlyInAnyOrder("plateau","famille");
    }
}