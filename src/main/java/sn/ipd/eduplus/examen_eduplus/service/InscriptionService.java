package sn.ipd.eduplus.examen_eduplus.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.ipd.eduplus.examen_eduplus.domain.Inscription;

public interface InscriptionService {

    // Inscrire un étudiant à un cours via leurs clés métiers
    Inscription inscrireEtudiant(String matricule, String codeCours);

    // Récupérer toutes les inscriptions de manière paginée (Exigence S2)
    Page<Inscription> getAllInscriptions(Pageable pageable);

    // Permettre à un enseignant ou admin d'attribuer une note
    Inscription attribuerNote(Long inscriptionId, Double note);
}