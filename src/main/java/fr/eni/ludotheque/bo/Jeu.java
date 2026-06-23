package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="JEUX")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Jeu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_jeu")
    private Long id;

    @Column(length=50, nullable=false)
    @NonNull private String titre;

    @Column(length=13, nullable=false, unique=true)
    @NonNull private String reference;

    @Column(name = "age_min", nullable=true)
    private int ageMin;

    @Column( nullable=true)
    private String description;

    @NonNull private int duree;

    @Column(name = "tarif_jour", nullable=false)
    @NonNull private Float tarifJour;

    @Transient
    private int nbExemplairesDisponibles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "JEUX_GENRES",
            joinColumns = @JoinColumn(name = "no_jeu"),
            inverseJoinColumns = @JoinColumn(name = "no_genre")
    )
    private List<Genre> genres = new ArrayList<>();

    public void addGenre(Genre g) {
        genres.add(g);
    }

}
