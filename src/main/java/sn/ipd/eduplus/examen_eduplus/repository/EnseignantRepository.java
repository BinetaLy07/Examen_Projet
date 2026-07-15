package sn.ipd.eduplus.examen_eduplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ipd.eduplus.examen_eduplus.domain.Enseignant;

import java.util.Optional;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {

    // Recherche un enseignant par son matricule unique
    Optional<Enseignant> findByMatricule(String matricule);

    // Vérifie si un matricule existe déjà en base de données
    boolean existsByMatricule(String matricule);
}