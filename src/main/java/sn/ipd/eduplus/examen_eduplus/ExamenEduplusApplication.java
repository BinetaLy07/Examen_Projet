package sn.ipd.eduplus.examen_eduplus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sn.ipd.eduplus.examen_eduplus.service.FileStorageService;

@SpringBootApplication
public class ExamenEduplusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenEduplusApplication.class, args);
	}

	// Ce bloc va appeler automatiquement la méthode init() de ton FileStorageService au démarrage
	@Bean
	CommandLineRunner start(FileStorageService fileStorageService) {
		return args -> {
			fileStorageService.init();
		};
	}
}