package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="EXEMPLAIRE")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_exemplaire")
    private Long id;

    @NonNull
    private String codebarre;

    @NonNull
    private Boolean estLouable;

    @ManyToOne
    @JoinColumn(name = "no_jeu")
    private Jeu jeu;

}
