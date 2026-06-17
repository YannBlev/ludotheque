package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="JEU")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Jeu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_jeu")
    private Long id;

    @NonNull
    private String titre;

    @NonNull
    private String reference;

    @Column(name = "age_min")
    private Integer ageMin;

    @NonNull
    private String description;

    @NonNull
    private Integer duree;

    @NonNull
    @Column(name = "tarif_jour")
    private Long tarifJour;

}
