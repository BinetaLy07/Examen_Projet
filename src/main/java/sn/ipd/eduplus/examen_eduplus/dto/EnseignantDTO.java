package sn.ipd.eduplus.examen_eduplus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnseignantDTO {

    @NotBlank(message = "Le matricule de l'enseignant est obligatoire")
    @Size(max = 20, message = "Le matricule ne doit pas dépasser 20 caractères")
    private String matricule;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "La spécialité est obligatoire")
    private String specialite;
}