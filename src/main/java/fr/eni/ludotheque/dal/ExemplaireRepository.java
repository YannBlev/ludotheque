package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {
    @Query(nativeQuery = true,
            value="""
        select count(*)
        from exemplaires e
        where e.no_jeu = :noJeu
        and e.louable = 1
        and e.no_exemplaire not in (
            select l.no_exemplaire
            from locations l
            where l.date_retour is null
        )
        """)
    int nbExemplairesDisponibleByNoJeu(@Param("noJeu") Long noJeu);

    //@Query("SELECT e FROM Exemplaire e JOIN FETCH e.jeu WHERE e.codebarre = :codebarre")
    //Exemplaire findByCodebarreWithJeu(@Param("codebarre") String codebarre);

    Optional<Exemplaire> findByCodebarre(String codebarre);
}
