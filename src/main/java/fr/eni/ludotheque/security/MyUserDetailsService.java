package fr.eni.ludotheque.security;

import fr.eni.ludotheque.bo.Role;
import fr.eni.ludotheque.bo.Utilisateur;
import fr.eni.ludotheque.dal.UtilisateurRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    public MyUserDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Utilisateur user = utilisateurRepository.findByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        String[] roles = user.getRoles()
                .stream()
                .map(Role::getLibelle)
                .toArray(String[]::new);

        return User
                .withUsername(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}