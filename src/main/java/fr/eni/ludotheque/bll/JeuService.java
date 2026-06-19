package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.bo.dto.JeuNbExemplaireDisponibleDto;

import java.util.List;

public interface JeuService {
    Jeu ajouterJeu(Jeu jeu);
    boolean estDisponible(Jeu jeu);
    List<JeuNbExemplaireDisponibleDto> listeJeuxDisponibles(Long filtreNoGenre);
}
