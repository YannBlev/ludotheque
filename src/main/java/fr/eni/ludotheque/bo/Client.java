package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="CLIENT")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_client")
    private Long id;

    @NonNull
    private String nom;

    @NonNull
    private String prenom;

    @NonNull
    private String email;

    @Column(name = "no_telephone")
    private String telephone;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="no_adresse")
    private Adresse adresse;


}
