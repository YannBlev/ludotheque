package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    //@NonNull
    @Column(name = "tarif_jour")
    private Float tarifJour;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "JEU_GENRE",
            joinColumns = @JoinColumn(name = "no_jeu"),
            inverseJoinColumns = @JoinColumn(name = "no_genre")
    )
    private List<Genre> genres = new ArrayList<>();

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

}
