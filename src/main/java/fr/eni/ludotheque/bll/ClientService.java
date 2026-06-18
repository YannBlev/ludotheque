package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client ajouterClient(Client client);
    List<Client> trouverParNomClient(String nom);
    Client modifierClient(Client client);
}
