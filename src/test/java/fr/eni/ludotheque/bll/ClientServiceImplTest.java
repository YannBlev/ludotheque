package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
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
        assertThat(clientBD).isNotNull();
        Assertions.assertNotNull(clientBD);
        Assertions.assertEquals(client,  clientBD);


    }

    @Test
    void testTrouverClient() {
        //Arrange (Préparation du test)

        //Act (Appel de la méthode à vérifier)
        List<Client> clients = clientService.trouverParNomClient("Pi");

        //Assert
        clients.forEach(System.out::println);
        assertThat(clients.stream().count()).isEqualTo(2);

    }

}
