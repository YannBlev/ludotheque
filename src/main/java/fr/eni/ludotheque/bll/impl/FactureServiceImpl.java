package fr.eni.ludotheque.bll.impl;

import fr.eni.ludotheque.bll.FactureService;
import fr.eni.ludotheque.bo.Facture;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dal.FactureRepository;
import fr.eni.ludotheque.dal.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactureServiceImpl implements FactureService {

    FactureRepository factureRepository;

    @Autowired
    public FactureServiceImpl(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    @Override
    public Facture creerFacture(Facture facture) {
        return factureRepository.save(facture);
    }
}
