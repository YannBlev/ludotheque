package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="FACTURES")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Facture {

    @Id
    @Column(name = "no_facture")
    private Long id;

    @Column(name="date_paiement")
    private LocalDateTime datePaiement;

    @OneToMany
    @JoinColumn(name="no_facture")
    private List<Location> locations=new ArrayList<Location>();


    @Transient
    private float prix;

    public void addLocation(Location location) {
        this.locations.add(location);
    }

}
