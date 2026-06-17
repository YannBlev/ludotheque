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

        // Recherche des genres présents dans import.sql
        Genre plateau = genreRepository.findByLibelle("plateau").orElseThrow();
        Genre famille = genreRepository.findByLibelle("famille").orElseThrow();


        // Création d'un jeu
        Jeu puzzle = new Jeu();
        puzzle.setTitre("Puzzle");
        puzzle.setReference("PUZ-001");
        puzzle.setDescription("Est-ce vraiment un jeu ?");
        puzzle.setDuree(90);
        puzzle.setTarifJour(3.5f);

        puzzle.addGenre(plateau);
        puzzle.addGenre(famille);

        // Sauvegarde
        Jeu jeuSauve = jeuRepository.save(puzzle);

        // Lecture depuis la base
        Jeu jeuRelu = jeuRepository.findById(jeuSauve.getId()).orElseThrow();

        // Vérifications
        assertThat(jeuRelu.getTitre()).isEqualTo("Puzzle");
        assertThat(jeuRelu.getReference()).isEqualTo("PUZ-001");
        assertThat(jeuRelu.getGenres()).hasSize(2);
        assertThat(jeuRelu.getGenres()).extracting(Genre::getLibelle).containsExactlyInAnyOrder("plateau","famille");
    }
}