package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value= """
            SELECT COUNT(*) FROM LOCATIONS
            WHERE date_retour IS NOT NULL AND no_facture IS NULL AND no_location = ?1
    """, nativeQuery = true)
    Integer countLocationByIdRetourSansFacture(Long idLocation);

    @Query(value= """
            SELECT COUNT(*) FROM LOCATIONS
            WHERE no_facture IS NULL AND no_location = ?1
    """, nativeQuery = true)
    Integer countSansFacture(Long idLocation);

}