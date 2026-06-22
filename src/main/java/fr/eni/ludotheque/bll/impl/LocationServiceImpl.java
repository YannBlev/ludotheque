package fr.eni.ludotheque.bll.impl;

import fr.eni.ludotheque.bll.FactureService;
import fr.eni.ludotheque.bll.LocationService;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Facture;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dal.FactureRepository;
import fr.eni.ludotheque.dal.LocationRepository;
import fr.eni.ludotheque.exception.FormatInvalide;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LocationServiceImpl implements LocationService {

    private final FactureRepository factureRepository;
    private final FactureService factureService;
    private LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, FactureRepository factureRepository, FactureService factureService) {
        this.locationRepository = locationRepository;
        this.factureRepository = factureRepository;
        this.factureService = factureService;
    }

    @Override
    @Transactional
    public Location creerLocation(Client client, Exemplaire exemplaire) {

        if (!exemplaire.getLouable()) throw new FormatInvalide("L'exemplaire n'est pas louable");

        exemplaire.setLouable(false);
        Location location = new Location();
        location.setClient(client);
        location.setExemplaire(exemplaire);
        location.setDateDebut(LocalDate.now());

        return locationRepository.save(location);
    }

    @Override
    public void modifierLocation(Location location) {

        if (sansFacture(location)) {
            Facture facture = new Facture();
            facture.setId(location.getId());
            factureService.creerFacture(facture);
        }

        locationRepository.save(location);
    }

    @Override
    public boolean estRetourneSansFacture(Location location) {
        return locationRepository.countLocationByIdRetourSansFacture(location.getId()) > 0;
    }

    @Override
    public boolean sansFacture(Location location) {
        return false;
    }
}
