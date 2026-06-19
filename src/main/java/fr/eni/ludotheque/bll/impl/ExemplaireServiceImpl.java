package fr.eni.ludotheque.bll.impl;

import fr.eni.ludotheque.bll.ExemplaireService;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExemplaireServiceImpl implements ExemplaireService {

    ExemplaireRepository exemplaireRepository;

    @Autowired
    public ExemplaireServiceImpl(ExemplaireRepository exemplaireRepository) {
        this.exemplaireRepository = exemplaireRepository;
    }
}
