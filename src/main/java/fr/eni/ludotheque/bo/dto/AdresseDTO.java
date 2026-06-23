package fr.eni.ludotheque.bo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdresseDTO {
    private String rue;
    private String codePostal;
    private String ville;
}
