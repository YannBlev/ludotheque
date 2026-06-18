package fr.eni.ludotheque.bll.impl;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public  ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
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
}