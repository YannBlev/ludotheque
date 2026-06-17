package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JeuRepository extends JpaRepository<Jeu, Long> {
    Optional<Jeu> findByTitre(String titre);
}
