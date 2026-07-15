package sn.ipd.eduplus.examen_eduplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ipd.eduplus.examen_eduplus.domain.AppUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    // Utilisé lors de la connexion pour retrouver l'utilisateur par son email
    Optional<AppUser> findByEmail(String email);

    // Utilisé lors de l'inscription pour vérifier si l'email n'est pas déjà pris
    boolean existsByEmail(String email);
}