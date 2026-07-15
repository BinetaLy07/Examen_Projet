package sn.ipd.eduplus.examen_eduplus.service.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ipd.eduplus.examen_eduplus.domain.Cours;
import sn.ipd.eduplus.examen_eduplus.repository.CoursRepository;
import sn.ipd.eduplus.examen_eduplus.service.CoursService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CoursServiceImpl implements CoursService {

    private final CoursRepository coursRepository;

    @Override
    public Cours saveCours(Cours cours) {
        // Règle métier : On s'assure que le code du cours est unique
        if (coursRepository.existsByCode(cours.getCode())) {
            throw new RuntimeException("Un cours avec ce code unique existe déjà !");
        }
        return coursRepository.save(cours);
    }

    @Override
    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    @Override
    public Cours getCoursByCode(String code) {
        return coursRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Cours introuvable avec le code : " + code));
    }

    @Override
    public void deleteCours(Long id) {
        if (!coursRepository.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : Cours introuvable !");
        }
        coursRepository.deleteById(id);
    }
}