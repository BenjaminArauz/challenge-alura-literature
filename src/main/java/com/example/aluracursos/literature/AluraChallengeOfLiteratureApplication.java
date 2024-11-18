package com.example.aluracursos.literature;

import com.example.aluracursos.literature.main.Main;
import com.example.aluracursos.literature.repository.AuthorRepository;
import com.example.aluracursos.literature.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AluraChallengeOfLiteratureApplication implements CommandLineRunner {
	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;

	@Autowired
	public AluraChallengeOfLiteratureApplication(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AluraChallengeOfLiteratureApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(bookRepository, authorRepository);
		main.showMenu();
	}
}
