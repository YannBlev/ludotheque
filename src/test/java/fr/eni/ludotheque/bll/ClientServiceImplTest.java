package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientServiceImplTest {
    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

    @Test
    void testCreationClient() {
        //Arrange (Préparation du test)
        Adresse adresse = new Adresse("rue des Cormorans", "44800", "Saint Herblain");
        Client client = new Client("Stiller", "Ben", "ben.stiller@eni.fr", adresse);
        client.setNoTelephone("0101010101");

        //Act (Appel de la méthode à vérifier)
        clientService.ajouterClient(client);

        //Assert (vérifications)
        Client clientBD = clientRepository.findById(client.getId()).orElse(null);
        Assertions.assertNotNull(clientBD);
        Assertions.assertEquals(client,  clientBD);


    }

}
