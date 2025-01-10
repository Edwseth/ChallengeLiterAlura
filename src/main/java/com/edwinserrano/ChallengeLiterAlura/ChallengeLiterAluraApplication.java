package com.edwinserrano.ChallengeLiterAlura;

import com.edwinserrano.ChallengeLiterAlura.principal.Principal;
import com.edwinserrano.ChallengeLiterAlura.repository.AuthorRepository;
import com.edwinserrano.ChallengeLiterAlura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu menu = new Menu(bookRepository, authorRepository);
		menu.showMenu();
	}
}

