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

    @Column(length = 50, nullable = false)
    @NonNull private String nom;

    @Column(length = 50, nullable = false)
    @NonNull private String prenom;

    @Column(length = 50, nullable = false, unique = true)
    @NonNull private String email;

    @Column(name = "no_telephone", length = 15, nullable = true)
    private String noTelephone;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER, orphanRemoval = true, optional = false)
    @JoinColumn(name="no_adresse")
    @NonNull private Adresse adresse;

}
