package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.LocationService;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.bo.dto.LocationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locations")
public class LocationRestController {

    LocationService locationService;

    public LocationRestController(LocationService locationService) {
        this.locationService = locationService;
    }

    //POST
    @PostMapping
    public ResponseEntity<ApiResponse<Location>> ajouterLocation(@RequestBody LocationDTO locationDTO, BindingResult result) {
        if (result.hasErrors()) {return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);}
        Location locationAjoute = locationService.ajouterLocation(locationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Ajout ok", locationAjoute));

    }
}
