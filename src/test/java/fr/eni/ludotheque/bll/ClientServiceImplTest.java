package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.AdresseRepository;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.exception.FormatInvalide;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class ClientServiceImplTest {
    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    private AdresseRepository adresseRepository;

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

    @Test
    void testModifierClientPositif() {
        //Arrange

        Adresse adresse = adresseRepository.findById(1L).orElse(null);

        Client client = clientRepository.findById(1L).orElse(null);
        System.out.println("*****************************************");
        System.out.println(client);
        System.out.println("*****************************************");

        //Act
        Client client2 = new Client();
        client2.setId(1L);
        client2.setNom("Stiller");
        client2.setPrenom("Ben");
        client2.setEmail("nawak@eni.fr");
        client2.setAdresse(adresse);

        clientService.modifierClient(client2);

        //Assert
        Client clientBD = clientRepository.findById(1L).orElse(null);
        System.out.println("*****************************************");
        System.out.println(clientBD);
        System.out.println("*****************************************");
        assertThat(clientBD.getNom()).isEqualTo("Stiller");
        assertThat(clientBD.getPrenom()).isEqualTo("Ben");
        assertThat(clientBD.getEmail()).isEqualTo("nawak@eni.fr");
        assertThat(clientBD.getAdresse()).isEqualTo(adresse);

    }

    @Test
    void testModifierClientException() {
        // Arrange
        Adresse adresse = adresseRepository.findById(1L).orElse(null);

        Client client = new Client();
        client.setNom("Stiller");
        client.setPrenom("Ben");
        client.setEmail("nawak@eni.fr");
        client.setAdresse(adresse);

        // Act + Assert
        FormatInvalide exception = Assertions.assertThrows(
                FormatInvalide.class,
                () -> clientService.modifierClient(client)
        );

        Assertions.assertEquals("ID inexistant", exception.getMessage());
    }
}
