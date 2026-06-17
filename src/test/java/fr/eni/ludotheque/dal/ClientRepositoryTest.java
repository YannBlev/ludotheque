package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Client;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    @Test
    @Transactional
    public void testInsertClientCasPositif(){
        //AAA
        //Arrange (préparer)
        clientRepository.deleteAll();
        Client anna = new Client();
        anna.setNom("BIHAN");
        anna.setPrenom("Anna");
        anna.setEmail("anna.bihan@email.test");

        //Act
        Client clientBD = clientRepository.save(anna);

        //Assert (vérifier)
        assertThat(clientBD).isNotNull();
        assertThat(clientBD.getId()).isNotNull();
        assertThat(clientBD.getNom()).isNotNull();
    }

}
