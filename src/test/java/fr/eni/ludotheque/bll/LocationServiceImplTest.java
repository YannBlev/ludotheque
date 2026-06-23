package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Facture;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.bo.dto.LocationDTO;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import fr.eni.ludotheque.dal.LocationRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class LocationServiceImplTest {

    @Autowired LocationService locationService;
    @Autowired ClientRepository clientRepository;

    @Test
    public void testAjoutLocation() {
        //Arrange
        Client client = clientRepository.findByNoTelephone("0202020202");

        LocationDTO locationDTO = new LocationDTO(client.getId(), "6666666666666");

        //Act
        Location location = locationService.ajouterLocation(locationDTO);

        //Assert
        Assertions.assertThat(location).isNotNull();
        Assertions.assertThat(location.getDateDebut()).isNotNull();
        Assertions.assertThat(location.getDateRetour()).isNull();
        Assertions.assertThat(location.getTarifJour()).isEqualTo(9.3f);
    }

    @Test
    @DisplayName("Test du retour d'exemplaire et creation de la facture")
    public void testRetourExemplairesEtCreationFacture() {
        //Arrange
        Client client = clientRepository.findByNoTelephone("0202020202");
        LocationDTO locationDTO1 = new LocationDTO(client.getId(), "6666666666666");
        Location loc1 = locationService.ajouterLocation(locationDTO1);
        LocationDTO locationDTO2 = new LocationDTO(client.getId(), "1111111111111");
        Location loc2 = locationService.ajouterLocation(locationDTO2);

        List<String> codebarres = List.of("1111111111111", "6666666666666");

        //act
        Facture facture = locationService.retourExemplaires(codebarres);

        //Assert
        Assertions.assertThat(facture).isNotNull();
        Assertions.assertThat(facture.getPrix()).isEqualTo(21.8f);
        Assertions.assertThat(facture.getLocations()).hasSize(2);
    }

    @Test
    @DisplayName("Test payer facture")
    public void testPayerFacture() {
        //Arrange
        Client client = clientRepository.findByNoTelephone("0202020202");
        LocationDTO locationDTO1 = new LocationDTO(client.getId(), "6666666666666");
        Location loc1 = locationService.ajouterLocation(locationDTO1);
        LocationDTO locationDTO2 = new LocationDTO(client.getId(), "1111111111111");
        Location loc2 = locationService.ajouterLocation(locationDTO2);
        List<String> codebarres = List.of("1111111111111", "6666666666666");
        Facture facture = locationService.retourExemplaires(codebarres);

        //act
        Facture facture2 = locationService.payerFacture(facture.getId());

        //Assert
        Assertions.assertThat(facture2).isNotNull();
        Assertions.assertThat(facture2.getDatePaiement()).isNotNull();
    }
}
