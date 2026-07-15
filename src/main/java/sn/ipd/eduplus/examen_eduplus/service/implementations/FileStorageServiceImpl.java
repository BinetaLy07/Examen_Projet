package sn.ipd.eduplus.examen_eduplus.service.implementations;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sn.ipd.eduplus.examen_eduplus.service.FileStorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    // Le dossier racine où seront stockés tous les uploads
    private final Path rootLocation = Paths.get("uploads");

    @Override
    public void init() {
        try {
            // Crée le dossier "uploads" s'il n'existe pas encore
            Files.createDirectories(rootLocation);
            Files.createDirectories(rootLocation.resolve("photos"));
            Files.createDirectories(rootLocation.resolve("cours"));
        } catch (IOException e) {
            throw new RuntimeException("Impossible d'initialiser le dossier de stockage des fichiers", e);
        }
    }

    @Override
    public String storeFile(MultipartFile file, String folder) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Impossible de stocker un fichier vide.");
            }

            // Générer un nom unique pour éviter d'écraser un fichier portant le même nom
            String originalFileName = file.getOriginalFilename();
            String extension = "";
            if (originalFileName != null && originalFileName.contains(".")) {
                extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }
            String uniqueFileName = UUID.randomUUID().toString() + extension;

            // Définir la destination (ex: uploads/photos ou uploads/cours)
            Path destinationFolder = this.rootLocation.resolve(folder);
            if (!Files.exists(destinationFolder)) {
                Files.createDirectories(destinationFolder);
            }

            Path destinationFile = destinationFolder.resolve(Paths.get(uniqueFileName))
                    .normalize().toAbsolutePath();

            // Copier le fichier physiquement sur le disque dur
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            // Retourner le chemin relatif pour l'enregistrer en Base de Données (ex: photos/mon-image.png)
            return folder + "/" + uniqueFileName;

        } catch (IOException e) {
            throw new RuntimeException("Échec de l'enregistrement du fichier", e);
        }
    }
}