package sn.ipd.eduplus.examen_eduplus.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.ipd.eduplus.examen_eduplus.domain.Etudiant;

public interface EtudiantService {

    // Enregistrer un nouvel étudiant
    Etudiant saveEtudiant(Etudiant etudiant);

    // Récupérer la liste des étudiants sous forme de pagination (Exigence S2)
    Page<Etudiant> getAllEtudiants(Pageable pageable);

    // Rechercher un étudiant par sa clé métier (le matricule)
    Etudiant getEtudiantByMatricule(String matricule);

    // Supprimer un étudiant par son identifiant unique
    void deleteEtudiant(Long id);
}