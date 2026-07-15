package sn.ipd.eduplus.examen_eduplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ipd.eduplus.examen_eduplus.domain.Cours;

import java.util.Optional;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
    Optional<Cours> findByCode(String code);
    boolean existsByCode(String code);
}