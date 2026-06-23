package fr.eni.ludotheque.bll.impl;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.dto.AdresseDTO;
import fr.eni.ludotheque.bo.dto.ClientDTO;
import fr.eni.ludotheque.dal.AdresseRepository;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.exception.ClientNotFoundException;
import fr.eni.ludotheque.exception.DataNotFound;
import fr.eni.ludotheque.exception.EmailClientAlreadyExistException;
import fr.eni.ludotheque.exception.FormatInvalide;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    @NonNull private final ClientRepository clientRepository;
    @NonNull private AdresseRepository adresseRepository;

    @Override
    public Client ajouterClient(ClientDTO clientDto)  {

        Client client = new Client();
        Adresse adresse = new Adresse();
        BeanUtils.copyProperties(clientDto, client);
        BeanUtils.copyProperties(clientDto, adresse);
        client.setAdresse(adresse);
        Client newClient = null;
        try {
            newClient = clientRepository.save(client);
        }catch(DataIntegrityViolationException ex) {
            throw new EmailClientAlreadyExistException();
        }

        return newClient;
    }

    @Override
    public List<Client> trouverClientsParNom(String nom) {
        return clientRepository.findByNomStartsWith(nom);
    }

    @Override
    public Client trouverClientParId(Long id)  {

        Optional<Client> optClient = clientRepository.findById(id);
        if(optClient.isEmpty()) {
            throw new ClientNotFoundException(id);
        }
        return optClient.get();
    }

    @Override
    public Client modifierClient(Long noClient, ClientDTO clientDto) {
        Client client = clientRepository.findById(noClient).orElseThrow(()->new DataNotFound("Client", noClient));

        client.setAdresse(new Adresse());
        BeanUtils.copyProperties(clientDto, client);
        BeanUtils.copyProperties(clientDto, client.getAdresse());
        Client clientBD = null;
        try {
            clientBD = clientRepository.save(client);
        } catch (OptimisticLockingFailureException e) {//thrown if entity is assumed to be present but does not exists in db
            //e.printStackTrace();
            throw new DataNotFound("Client", noClient);
        }

        return clientBD;
    }

    @Override
    public Client modifierAdresse(Long noClient, AdresseDTO adresseDto) {
        Client client = clientRepository.findById(noClient).orElseThrow(()->new ClientNotFoundException(noClient));

        BeanUtils.copyProperties(adresseDto, client.getAdresse());

        System.out.println("***********************************************");
        System.out.println("adresse : " + client.getAdresse());
        System.out.println("***********************************************");

        adresseRepository.save(client.getAdresse());

        return client;

    }

    @Override
    public void supprimerClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new ClientNotFoundException(id);
        }
        clientRepository.deleteById(id);
    }


}