package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.bo.dto.JeuNbExemplaireDisponibleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JeuRepository extends JpaRepository<Jeu, Long> {
    Optional<Jeu> findByTitre(String titre);

    @Query(value = """
        SELECT COUNT(e.no_exemplaire)
        FROM JEUX j
        LEFT JOIN EXEMPLAIRES e ON j.no_jeu = e.no_jeu
        LEFT JOIN LOCATIONS l ON e.no_exemplaire = l.no_exemplaire
        WHERE l.no_location IS NULL
        AND j.titre = ?1
    """, nativeQuery = true)
    Long countDisponibles(String titre);

    @Query(value= """
        SELECT j.titre, g.libelle, COUNT(e.no_exemplaire) 'nbExemplaire'
        FROM JEUX AS j
        INNER JOIN JEUX_GENRES j_g
        ON j.no_jeu = j_g.no_jeu
        INNER JOIN GENRES g
        ON j_g.no_genre = g.no_genre
        LEFT JOIN EXEMPLAIRES e
        ON j.no_jeu = e.no_jeu
        LEFT JOIN LOCATIONS l
        ON e.no_exemplaire = l.no_exemplaire
        WHERE l.no_location IS NULL
            AND (:filtreNoGenre IS NULL OR j_g.no_genre = :filtreNoGenre) 
        GROUP BY j.titre, g.libelle
    """, nativeQuery = true)
    List<JeuNbExemplaireDisponibleDto> listJeuxDisponibles(@Param("filtreNoGenre") Long filtreNoGenre);
}
