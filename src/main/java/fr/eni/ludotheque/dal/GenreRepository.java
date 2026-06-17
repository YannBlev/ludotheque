package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByLibelle(String libelle);
}
