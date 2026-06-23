package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Exemplaire;

public interface ExemplaireService {
    Exemplaire trouverExemplaireParCodeBarre(String codebarre);
}
