package sn.ipd.eduplus.examen_eduplus.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sn.ipd.eduplus.examen_eduplus.domain.Enseignant;
import sn.ipd.eduplus.examen_eduplus.dto.EnseignantDTO;
import sn.ipd.eduplus.examen_eduplus.service.EnseignantService;

@RestController
@RequestMapping("/api/enseignants")
@RequiredArgsConstructor
public class EnseignantController {

    private final EnseignantService enseignantService;

    // Ajouter un enseignant en utilisant le DTO et @Valid
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Enseignant> createEnseignant(@Valid @RequestBody EnseignantDTO dto) {
        Enseignant enseignant = Enseignant.builder()
                .matricule(dto.getMatricule())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .specialite(dto.getSpecialite())
                .build();

        return new ResponseEntity<>(enseignantService.saveEnseignant(enseignant), HttpStatus.CREATED);
    }

    // Liste paginée des enseignants via le service
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public ResponseEntity<Page<Enseignant>> getAllEnseignants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(enseignantService.getAllEnseignants(pageable));
    }
}