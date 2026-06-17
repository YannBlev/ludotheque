package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="ADRESSE")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_adresse")
    private Long id;

    @NonNull
    private String rue;

    @NonNull
    @Column(name = "code_postal")
    private String code;

    @NonNull
    private String ville;

}
