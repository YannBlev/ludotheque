package fr.eni.ludotheque.bll.impl;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.exception.FormatInvalide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public  ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public List<Client> listerClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client ajouterClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> trouverParNomClient(String nom) {
        List<Client> clients = clientRepository.findAll();
        List<Client> clientsTrouves = new ArrayList<>();
        clients.forEach(client -> {
            if (client.getNom().startsWith(nom)) {
                clientsTrouves.add(client);
            }
        });
        return clientsTrouves;
    }

    @Override
    public boolean clientValide(Client client) {
        if (client.getId() == null) throw new FormatInvalide("ID inexistant");
        if (!clientRepository.existsById(client.getId()))  throw new FormatInvalide("Client introuvable");
        return true;
    }

    @Override
    public void modifierClient(Client client) {
        if(clientValide(client)) clientRepository.save(client);
    }

    @Override
    public void modifierAdresseClient(Adresse adresse, Client client) {
        if(clientValide(client)) {
            client.setAdresse(adresse);
            clientRepository.save(client);
        };
    }


}