package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.dto.AdresseDTO;
import fr.eni.ludotheque.bo.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    public Client ajouterClient(ClientDTO clientDto);

    public List<Client> trouverClientsParNom(String nom);

    public Client modifierClient(Long noClient, ClientDTO clientDto);

    public Client trouverClientParId(Long id);

    public Client modifierAdresse(Long noClient, AdresseDTO adresseDto) ;

    void supprimerClient(Long id);
}
