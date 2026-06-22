package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="LOCATIONS")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="no_location")
    private Long id;

    @Column(name="date_debut", nullable=false)
    @NonNull private LocalDate dateDebut;

    @Column(name="date_retour")
    private LocalDate dateRetour;

    @ManyToOne
    @JoinColumn(name="no_client", referencedColumnName = "no_client")
    @NonNull private Client client;

    @ManyToOne
    @JoinColumn(name="no_exemplaire", referencedColumnName = "no_exemplaire")
    @NonNull private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name="no_facture", referencedColumnName = "no_facture")
    private Facture facture;

}
