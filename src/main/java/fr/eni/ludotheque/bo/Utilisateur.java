package fr.eni.ludotheque.bo;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="UTILISATEURS")
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noUtilisateur;
	@Column(unique = true)
	@NonNull
	private String login;
	@NonNull
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "UTILISATEURS_ROLES",
			joinColumns = @JoinColumn(name = "no_utilisateur"),
			inverseJoinColumns = @JoinColumn(name = "no_role"))
	private List<Role> roles;
}