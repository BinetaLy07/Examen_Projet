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
import sn.ipd.eduplus.examen_eduplus.domain.Etudiant;
import sn.ipd.eduplus.examen_eduplus.dto.EtudiantDTO;
import sn.ipd.eduplus.examen_eduplus.service.EtudiantService;

@RestController
@RequestMapping("/api/etudiants")
@RequiredArgsConstructor
public class EtudiantController {

    private final EtudiantService etudiantService;

    // 1. Utilisation de @Valid et du DTO pour l'enregistrement
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public ResponseEntity<Etudiant> createEtudiant(@Valid @RequestBody EtudiantDTO dto) {
        // Conversion du DTO vers l'Entité avant de sauvegarder
        Etudiant etudiant = Etudiant.builder()
                .matricule(dto.getMatricule())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .email(dto.getEmail())
                .filiere(dto.getFiliere())
                .build();

        return new ResponseEntity<>(etudiantService.saveEtudiant(etudiant), HttpStatus.CREATED);
    }

    // 2. Remplacement de la liste brute par un CRUD PAGINÉ
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT', 'ETUDIANT')")
    public ResponseEntity<Page<Etudiant>> getAllEtudiants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(etudiantService.getAllEtudiants(pageable));
    }

    @GetMapping("/{matricule}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT', 'ETUDIANT')")
    public ResponseEntity<Etudiant> getEtudiantByMatricule(@PathVariable String matricule) {
        return ResponseEntity.ok(etudiantService.getEtudiantByMatricule(matricule));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.noContent().build();
    }
}