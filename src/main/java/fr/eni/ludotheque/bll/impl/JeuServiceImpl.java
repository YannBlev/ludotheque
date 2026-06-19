package fr.eni.ludotheque.bll.impl;

import fr.eni.ludotheque.bll.JeuService;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.bo.dto.JeuNbExemplaireDisponibleDto;
import fr.eni.ludotheque.dal.JeuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JeuServiceImpl implements JeuService {

    private JeuRepository jeuRepository;

    @Autowired
    public JeuServiceImpl(JeuRepository jeuRepository) {
        this.jeuRepository = jeuRepository;
    }

    @Override
    public Jeu ajouterJeu(Jeu jeu) {
        return jeuRepository.save(jeu);
    }

    @Override
    public boolean estDisponible(Jeu jeu) {
        return jeuRepository.countDisponibles(jeu.getTitre()) > 0;
    }

    @Override
    public List<JeuNbExemplaireDisponibleDto> listeJeuxDisponibles(Long filtreNoGenre) {
        return jeuRepository.listJeuxDisponibles(filtreNoGenre);
    }


}
