package fr.eni.ludotheque.bll.impl;

import fr.eni.ludotheque.bll.ExemplaireService;
import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import fr.eni.ludotheque.exception.DataNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExemplaireServiceImpl implements ExemplaireService {

    ExemplaireRepository exemplaireRepository;

    @Autowired
    public ExemplaireServiceImpl(ExemplaireRepository exemplaireRepository) {
        this.exemplaireRepository = exemplaireRepository;
    }

    @Override
    public Exemplaire trouverExemplaireParCodeBarre(String codebarre) {
        Optional<Exemplaire> exemplaireOptional = exemplaireRepository.findByCodebarre(codebarre);
        if (exemplaireOptional.isEmpty()) throw new DataNotFound("Codebarre : ",  codebarre);
        return exemplaireOptional.get();
    }
}
