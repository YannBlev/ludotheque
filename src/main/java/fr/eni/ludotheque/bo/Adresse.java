package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="ADRESSES")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_adresse")
    private Long id;

    @Column(length = 100, nullable = false)
    @NonNull private String rue;

    @Column(name = "code_postal", length = 5, nullable = false)
    @NonNull private String codePostal;

    @Column(length = 100, nullable = false)
    @NonNull private String ville;

}
