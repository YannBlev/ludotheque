package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void ajouterClient_avecSucces() {

        // Arrange
        Adresse adresse = new Adresse(
                "10 rue de Paris",
                "44000",
                "Nantes"
        );

        Client client = new Client(
                "Dupont",
                "Jean",
                "jean.dupont@test.fr",
                adresse
        );

        when(clientRepository.save(any(Client.class)))
                .thenAnswer(invocation -> {
                    Client c = invocation.getArgument(0);
                    c.setId(1L);
                    return c;
                });

        // Act
        Client resultat = clientService.ajouterClient(client);

        // Assert
        assertAll(
                () -> assertNotNull(resultat),
                () -> assertEquals(1L, resultat.getId()),
                () -> assertEquals("Dupont", resultat.getNom()),
                () -> assertEquals("Jean", resultat.getPrenom()),
                () -> assertEquals("jean.dupont@test.fr", resultat.getEmail())
        );

        verify(clientRepository).save(client);
    }
}