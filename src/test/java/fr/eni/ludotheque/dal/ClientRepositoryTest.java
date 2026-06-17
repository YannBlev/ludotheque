package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import jakarta.transaction.Transactional;
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
    public void testInsertClientCasPositif(){
        //AAA
        //Arrange (préparer)
        clientRepository.deleteAll();
        adresseRepository.deleteAll();

        Adresse a1 = new Adresse("rue des lilas", "50000", "RODENDINDRON");

        Client anna = new Client();
        anna.setNom("BIHAN");
        anna.setPrenom("Anna");
        anna.setEmail("anna.bihan@email.test");

        anna.setAdresse(a1);

        //Act
        Client clientBD = clientRepository.save(anna);

        //Assert (vérifier)
        assertThat(clientBD).isNotNull();
        assertThat(clientBD.getId()).isNotNull();
        assertThat(clientBD.getNom()).isNotNull();
        assertThat(clientBD.getAdresse().getId()).isNotNull();
    }

}
