package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate datePaiement;

}
