package sn.ipd.eduplus.examen_eduplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ipd.eduplus.examen_eduplus.domain.AppUser;
import sn.ipd.eduplus.examen_eduplus.domain.RefreshToken;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    // Permet de retrouver un Refresh Token précis en base de données
    Optional<RefreshToken> findByToken(String token);

    // Permet de supprimer le token d'un utilisateur lors de sa déconnexion
    void deleteByUser(AppUser user);
}