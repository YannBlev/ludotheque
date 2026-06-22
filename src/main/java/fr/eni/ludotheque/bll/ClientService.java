package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Optional<Client> getClientById(Long id);
    List<Client> listerClients();
    Client ajouterClient(Client client);
    List<Client> trouverParNomClient(String nom);
    boolean clientValide(Client client);
    void modifierClient(Client client);
    void modifierAdresseClient(Adresse adresse, Client client);
    void supprimerClient(Long id);
}
