package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AdresseRepository adresseRepository;

    @Test
    @DisplayName("Test positif de creation d'un client en BD")
    public void testInsertClientCasPositif(){
        //AAA
        //Arrange (préparer)
        Adresse adresse = adresseRepository.findById(1L).orElseThrow();

        Client client = new Client();
        client.setNom("BIHAN");
        client.setPrenom("Anna");
        client.setEmail("anna.bihan@email.test");

        client.setAdresse(adresse);

        //Act
        clientRepository.save(client);

        //Assert (vérifier)
        Client clientBD = clientRepository.findById(client.getId()).orElseThrow();
        assertThat(clientBD).isNotNull();
        assertThat(clientBD.getId()).isNotNull();
        assertThat(clientBD.getNom()).isNotNull();
        assertThat(clientBD.getAdresse().getId()).isNotNull();
    }

}
