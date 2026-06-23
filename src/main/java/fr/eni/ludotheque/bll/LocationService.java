package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Facture;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.bo.dto.LocationDTO;

import java.time.LocalDate;
import java.util.List;

public interface LocationService {
    Location ajouterLocation(LocationDTO locationDto);

    Facture retourExemplaires(List<String> codebarres);

    Facture payerFacture( Long noFacture);

    void trouverLocationParExemplaireCodebarre(String codebarre);
}
