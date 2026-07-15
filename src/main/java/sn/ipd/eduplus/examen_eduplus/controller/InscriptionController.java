package sn.ipd.eduplus.examen_eduplus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sn.ipd.eduplus.examen_eduplus.domain.Inscription;
import sn.ipd.eduplus.examen_eduplus.service.InscriptionService; // Cet import règle le problème !

@RestController
@RequestMapping("/api/inscriptions")
@RequiredArgsConstructor
public class InscriptionController {

    private final InscriptionService inscriptionService;

    // Effectuer une inscription (ADMIN ou ENSEIGNANT)
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public ResponseEntity<Inscription> inscrire(
            @RequestParam String matricule,
            @RequestParam String codeCours
    ) {
        return new ResponseEntity<>(inscriptionService.inscrireEtudiant(matricule, codeCours), HttpStatus.CREATED);
    }

    // Liste paginée de toutes les inscriptions
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public ResponseEntity<Page<Inscription>> getAllInscriptions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(inscriptionService.getAllInscriptions(pageable));
    }

    // Attribuer une note (Seul l'ENSEIGNANT qui corrige ou l'ADMIN peut le faire)
    @PatchMapping("/{id}/note")
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public ResponseEntity<Inscription> attribuerNote(
            @PathVariable Long id,
            @RequestParam Double note
    ) {
        return ResponseEntity.ok(inscriptionService.attribuerNote(id, note));
    }
}