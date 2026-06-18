package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Location;

public interface LocationService {
    Location creerLocation(Client client, Exemplaire exemplaire);
}
