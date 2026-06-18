package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Genre;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class GenreRepositoryTest {

    @Autowired
    GenreRepository genreRepository;
    @Test
    public void testInsertGenreCasPositif(){
        //AAA
        //Arrange (préparer)

        Genre genre = new Genre();
        genre.setLibelle("deckbuilder");

        //Act
        genreRepository.save(genre);

        //Assert
        Genre genreBD = genreRepository.findById(genre.getId()).orElseThrow();
        assertThat(genreBD.getLibelle()).isEqualTo("deckbuilder");

    }
}
