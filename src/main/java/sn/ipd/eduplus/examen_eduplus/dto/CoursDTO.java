package sn.ipd.eduplus.examen_eduplus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoursDTO {

    @NotBlank(message = "Le code du cours est obligatoire")
    @Size(min = 2, max = 10, message = "Le code doit contenir entre 2 et 10 caractères")
    private String code;

    @NotBlank(message = "Le libellé du cours est obligatoire")
    private String libelle;

    @NotBlank(message = "La description est obligatoire")
    private String description;
}