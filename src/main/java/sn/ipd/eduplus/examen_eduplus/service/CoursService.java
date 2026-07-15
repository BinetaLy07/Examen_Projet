package sn.ipd.eduplus.examen_eduplus.service;

import sn.ipd.eduplus.examen_eduplus.domain.Cours;
import java.util.List;

public interface CoursService {
    Cours saveCours(Cours cours);
    List<Cours> getAllCours();
    Cours getCoursByCode(String code);
    void deleteCours(Long id);
}