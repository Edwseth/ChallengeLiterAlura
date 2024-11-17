package com.edwinserrano.ChallengeLiterAlura;

import com.edwinserrano.ChallengeLiterAlura.principal.Principal;
import com.edwinserrano.ChallengeLiterAlura.repository.AutorRepository;
import com.edwinserrano.ChallengeLiterAlura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiterAluraApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private AutorRepository autorRepository;
	private Principal principal;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository, autorRepository);
		principal.mostrarMenu();
	}
}

