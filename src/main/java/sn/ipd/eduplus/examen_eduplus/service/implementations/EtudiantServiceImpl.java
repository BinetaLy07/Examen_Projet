package sn.ipd.eduplus.examen_eduplus.service.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ipd.eduplus.examen_eduplus.domain.Etudiant;
import sn.ipd.eduplus.examen_eduplus.repository.EtudiantRepository;
import sn.ipd.eduplus.examen_eduplus.service.EtudiantService;

@Service
@RequiredArgsConstructor
@Transactional
public class EtudiantServiceImpl implements EtudiantService {

    private final EtudiantRepository etudiantRepository;

    @Override
    public Etudiant saveEtudiant(Etudiant etudiant) {
        // Règle métier : On vérifie si le matricule existe déjà avant d'enregistrer
        if (etudiantRepository.existsByMatricule(etudiant.getMatricule())) {
            throw new RuntimeException("Un étudiant avec ce matricule existe déjà !");
        }
        return etudiantRepository.save(etudiant);
    }

    @Override
    public Page<Etudiant> getAllEtudiants(Pageable pageable) {
        // Correction : Utilisation de la méthode paginée native de JpaRepository
        return etudiantRepository.findAll(pageable);
    }

    @Override
    public Etudiant getEtudiantByMatricule(String matricule) {
        return etudiantRepository.findByMatricule(matricule)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable avec le matricule : " + matricule));
    }

    @Override
    public void deleteEtudiant(Long id) {
        if (!etudiantRepository.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : Étudiant introuvable !");
        }
        etudiantRepository.deleteById(id);
    }
}