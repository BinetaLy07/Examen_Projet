package sn.ipd.eduplus.examen_eduplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ipd.eduplus.examen_eduplus.domain.Cours;
import sn.ipd.eduplus.examen_eduplus.domain.Etudiant;
import sn.ipd.eduplus.examen_eduplus.domain.Inscription;

import java.util.List;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

    // Récupérer toutes les inscriptions d'un étudiant spécifique
    List<Inscription> findByEtudiant(Etudiant etudiant);

    // Récupérer toutes les inscriptions liées à un cours donné
    List<Inscription> findByCours(Cours cours);

    // Règle métier : Permet de vérifier si un étudiant est déjà inscrit à un cours précis
    boolean existsByEtudiantAndCours(Etudiant etudiant, Cours cours);
}