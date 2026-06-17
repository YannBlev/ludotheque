package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class GenreRepositoryTest {

    @Autowired
    GenreRepository genreRepository;
    @Test
    //@Transactional
    public void testInsertGenreCasPositif(){
        //AAA
        //Arrange (préparer)

        genreRepository.deleteAll();
        Genre plateau = new Genre();
        plateau.setLibelle("plateau");

        //Act
        Genre plateauBD = genreRepository.save(plateau);

        //Assert
        assertThat(plateauBD.getLibelle()).isEqualTo("plateau");

    }
}
