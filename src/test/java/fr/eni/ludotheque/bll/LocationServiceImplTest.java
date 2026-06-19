package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import fr.eni.ludotheque.dal.LocationRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class LocationServiceImplTest {

    @Autowired
    LocationService locationService;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    ExemplaireRepository exemplaireRepository;
    @Autowired
    ClientRepository clientRepository;

    @Test
    public void testCreerLocation() {
        //Arrange
        Client client = clientRepository.findById(1L).orElseThrow();
        Exemplaire exemplaire = exemplaireRepository.findById(1L).orElseThrow();
        System.out.println("********************************************");
        System.out.println(exemplaire);
        System.out.println("********************************************");

        //Act
        locationService.creerLocation(client, exemplaire);

        //Arrange
        Location location = locationRepository.findById(5L).orElseThrow();
        assertThat(location.getClient()).isEqualTo(client);
        assertThat(location.getExemplaire()).isEqualTo(exemplaire);
        assertThat(location.getExemplaire().getLouable()).isEqualTo(false);

    }

}
