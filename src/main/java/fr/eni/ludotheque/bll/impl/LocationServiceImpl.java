package fr.eni.ludotheque.bll.impl;

import fr.eni.ludotheque.bll.FactureService;
import fr.eni.ludotheque.bll.LocationService;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Facture;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.bo.dto.LocationDTO;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import fr.eni.ludotheque.dal.FactureRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import fr.eni.ludotheque.dal.LocationRepository;
import fr.eni.ludotheque.exception.DataNotFound;
import fr.eni.ludotheque.exception.FormatInvalide;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{
    @NonNull
    final private LocationRepository locationRepository;

    @NonNull
    final private JeuRepository jeuRepository;

    @NonNull
    final private ExemplaireRepository exemplaireRepository;

    @NonNull
    final private FactureRepository factureRepository;


    @Override
    public Location ajouterLocation(LocationDTO locationDto  ) {
        //Exemplaire exemplaire = exemplaireRepository.findByCodebarreWithJeu(locationDto.getCodebarre());
        Optional<Exemplaire> exemplaireTrouve = exemplaireRepository.findByCodebarre(locationDto.getCodebarre());
        if (exemplaireTrouve.isEmpty()) {
            throw new DataNotFound("Codebarre : ",  locationDto.getCodebarre());
        }
        Client client = new Client();
        client.setId(locationDto.getNoClient());

        Location location = new Location(LocalDateTime.now(),client, exemplaireTrouve.get() );
        Float tarifJour = jeuRepository.findTarifJour(exemplaireTrouve.get().getJeu().getId());
        location.setTarifJour(tarifJour);
        Location newLoc = locationRepository.save(location);

        return newLoc;
    }

    @Override
    @Transactional
    public Facture retourExemplaires(List<String> codebarres) {
        Facture facture = new Facture();
        //facture
        Location location = null;
        float prix = 0;
        for(String codebarre : codebarres) {
            location = locationRepository.findLocationByCodebarreWithJeu(codebarre);
            location.setDateRetour(LocalDateTime.now());
            facture.addLocation(location);
            locationRepository.save(location);
            long nbJours = ChronoUnit.DAYS.between(location.getDateDebut(), location.getDateRetour()) +1;
            prix += (nbJours * location.getTarifJour());
        }
        facture.setPrix(prix);
        return factureRepository.save(facture);
    }


    public Facture payerFacture( Long noFacture){
        Facture facture = factureRepository.findById(noFacture).orElse(null);
        facture.setDatePaiement(LocalDateTime.now());
        Facture factureBD = factureRepository.save(facture);
        return factureBD;
    }

    @Override
    public void trouverLocationParExemplaireCodebarre(String codebarre) {
        // TODO Auto-generated method stub

    }

}
