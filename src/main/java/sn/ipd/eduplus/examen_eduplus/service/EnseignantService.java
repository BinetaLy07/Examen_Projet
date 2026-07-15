package sn.ipd.eduplus.examen_eduplus.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.ipd.eduplus.examen_eduplus.domain.Enseignant;

public interface EnseignantService {

    // Enregistrer un enseignant
    Enseignant saveEnseignant(Enseignant enseignant);

    // Liste paginée des enseignants (Exigence S2)
    Page<Enseignant> getAllEnseignants(Pageable pageable);

    // Rechercher un enseignant par son matricule
    Enseignant getEnseignantByMatricule(String matricule);
}