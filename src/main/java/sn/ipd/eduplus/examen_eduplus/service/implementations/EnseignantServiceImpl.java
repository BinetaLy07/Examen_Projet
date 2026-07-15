package sn.ipd.eduplus.examen_eduplus.service.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ipd.eduplus.examen_eduplus.domain.Enseignant;
import sn.ipd.eduplus.examen_eduplus.repository.EnseignantRepository;
import sn.ipd.eduplus.examen_eduplus.service.EnseignantService;

@Service
@RequiredArgsConstructor
@Transactional
public class EnseignantServiceImpl implements EnseignantService {

    private final EnseignantRepository enseignantRepository;

    @Override
    public Enseignant saveEnseignant(Enseignant enseignant) {
        if (enseignantRepository.existsByMatricule(enseignant.getMatricule())) {
            throw new RuntimeException("Un enseignant avec ce matricule existe déjà !");
        }
        return enseignantRepository.save(enseignant);
    }

    @Override
    public Page<Enseignant> getAllEnseignants(Pageable pageable) {
        return enseignantRepository.findAll(pageable);
    }

    @Override
    public Enseignant getEnseignantByMatricule(String matricule) {
        return enseignantRepository.findByMatricule(matricule)
                .orElseThrow(() -> new RuntimeException("Enseignant introuvable avec le matricule : " + matricule));
    }
}