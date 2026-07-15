package sn.ipd.eduplus.examen_eduplus.service.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ipd.eduplus.examen_eduplus.domain.Cours;
import sn.ipd.eduplus.examen_eduplus.domain.Etudiant;
import sn.ipd.eduplus.examen_eduplus.domain.Inscription;
import sn.ipd.eduplus.examen_eduplus.repository.CoursRepository;
import sn.ipd.eduplus.examen_eduplus.repository.EtudiantRepository;
import sn.ipd.eduplus.examen_eduplus.repository.InscriptionRepository;
import sn.ipd.eduplus.examen_eduplus.service.InscriptionService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class InscriptionServiceImpl implements InscriptionService {

    private final InscriptionRepository inscriptionRepository;
    private final EtudiantRepository etudiantRepository;
    private final CoursRepository coursRepository;

    @Override
    public Inscription inscrireEtudiant(String matricule, String codeCours) {
        // 1. Recherche de l'étudiant par son matricule
        Etudiant etudiant = etudiantRepository.findByMatricule(matricule)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable avec le matricule : " + matricule));

        // 2. Recherche du cours par son code unique
        Cours cours = coursRepository.findByCode(codeCours)
                .orElseThrow(() -> new RuntimeException("Cours introuvable avec le code : " + codeCours));

        // 3. Règle métier : On évite les doublons d'inscription
        if (inscriptionRepository.existsByEtudiantAndCours(etudiant, cours)) {
            throw new RuntimeException("Cet étudiant est déjà inscrit à ce cours !");
        }

        // 4. Création et sauvegarde de l'inscription
        Inscription inscription = Inscription.builder()
                .etudiant(etudiant)
                .cours(cours)
                .dateInscription(LocalDate.now())
                .build();

        return inscriptionRepository.save(inscription);
    }

    @Override
    public Page<Inscription> getAllInscriptions(Pageable pageable) {
        return inscriptionRepository.findAll(pageable);
    }

    @Override
    public Inscription attribuerNote(Long inscriptionId, Double note) {
        // Règle métier : Validation de la cohérence de la note
        if (note < 0 || note > 20) {
            throw new RuntimeException("La note doit réglementairement être comprise entre 0 et 20 !");
        }

        Inscription inscription = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new RuntimeException("Inscription introuvable avec l'ID : " + inscriptionId));

        inscription.setNote(note);
        return inscriptionRepository.save(inscription);
    }
}