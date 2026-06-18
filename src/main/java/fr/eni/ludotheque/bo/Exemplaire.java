package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="EXEMPLAIRES")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_exemplaire")
    private Long id;

    @Column(length=13, nullable = false, unique = true)
    @NonNull private String codebarre;

    @NonNull
    @Basic(optional = false)
    private Boolean louable;

    @ManyToOne
    @JoinColumn(name="no_jeu", referencedColumnName = "no_jeu")
    @NonNull private Jeu jeu;

}
