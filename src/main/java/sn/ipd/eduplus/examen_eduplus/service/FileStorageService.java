package sn.ipd.eduplus.examen_eduplus.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    // Initialise le dossier d'upload
    void init();

    // Enregistre un fichier et retourne son nom unique généré
    String storeFile(MultipartFile file, String folder);
}