package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Location;

import java.time.LocalDate;

public interface LocationService {
    Location creerLocation(Client client, Exemplaire exemplaire);
    void modifierLocation(Location location);
    boolean estRetourneSansFacture(Location location);
    boolean sansFacture(Location location);
}
