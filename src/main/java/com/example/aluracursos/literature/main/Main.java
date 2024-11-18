package com.example.aluracursos.literature.main;

import com.example.aluracursos.literature.models.data.DataBookResponse;
import com.example.aluracursos.literature.models.model.Author;
import com.example.aluracursos.literature.models.model.Book;
import com.example.aluracursos.literature.repository.AuthorRepository;
import com.example.aluracursos.literature.repository.BookRepository;
import com.example.aluracursos.literature.service.ConsumeAPI;
import com.example.aluracursos.literature.service.ConvertData;
import com.example.aluracursos.literature.utils.Constants;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    // Variables to be used in the class
    private Scanner scanner = new Scanner(System.in);
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private ConvertData converter = new ConvertData();
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    // Constructor
    public Main(BookRepository repository, AuthorRepository authorRepository) {
        this.bookRepository = repository;
        this.authorRepository = authorRepository;
    }

    // Method to show the menu
    public void showMenu(){
        var option = -1;
        while (option != 0) {
            var menu = """
                    1. Search book by title
                    2. Show searched books
                    3. Show searched authors
                    4. Show alive authors by year
                    5. Show books by idiom
                    0. Exit
                    """;

            System.out.println(menu);
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                // Switch statement to handle the options
                switch (option) {
                    case 1:
                        searchBook();
                        break;
                    case 2:
                        showBooks();
                        break;
                    case 3:
                        showAuthors();
                        break;
                    case 4:
                        showAuthorsByYear();
                        break;
                    case 5:
                        showBooksByLanguage();
                        break;
                    case 0:
                        System.out.println("Closing the application...");
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.out.println("Invalid option");
                scanner.nextLine();
            }

        }
    }

    // Method to get the data from the API
    private DataBookResponse getData() {
        System.out.println("Write the title of the book");
        String title = scanner.nextLine();
        // URL to get the data
        String url = Constants.URL + "search=" + title.replace(" ", "%20");
        String json = consumeAPI.getData(url);
        // Convert the data to the DataBookResponse class
        DataBookResponse data = converter.getData(json, DataBookResponse.class);
        return data;
    }

    public void addAuthors(Book book){
        Set<Author> managedAuthors = new HashSet<>();
        for (Author author : book.getAuthors()) {
            // Check if the author already exists in the database
            Optional<Author> existingAuthor = authorRepository.findByName(author.getName());
            if (existingAuthor.isPresent()) {
                // If the author already exists, add it to the managedAuthors set
                managedAuthors.add(existingAuthor.get());
            } else {
                // If the author does not exist, save it to the database
                // Add it to the managedAuthors set
                authorRepository.save(author);
                managedAuthors.add(author);
            }
        }
        // Set the managedAuthors set to the book
        book.setAuthors(managedAuthors);
    }

    // Method to add the books to the database
    public void addBooks(List<Book> books, int option){
        if (option == 0){
            for (Book book : books){
                // Check if the book already exists in the database
                Optional<Book> existingBook = bookRepository.findByTitle(book.getTitle());
                if (existingBook.isEmpty()){
                    // Add the authors and save it to the database
                    addAuthors(book);
                    bookRepository.save(book);
                } else {
                    System.out.println("Book with title " + book.getTitle() + " already exists.");
                }
            }
        } else {
            Book book = books.get(option - 1);
            // Check if the book already exists in the database
            Optional<Book> existingBook = bookRepository.findByTitle(book.getTitle());
            if (existingBook.isEmpty()){
                // Add the authors and save it to the database
                addAuthors(book);
                bookRepository.save(book);
            } else {
                System.out.println("Book with title " + book.getTitle() + " already exists.");
            }
        }
    }

    // Choose the book to add to the database
    public void selectBooks(List<Book> books){
        for (int i = 0; i < books.size(); i++) {
            System.out.println(i + 1 + ". " + books.get(i).getTitle());
        }
        System.out.println("0: All the books");
        System.out.println("Write the number of the book");
        // Get the option from the user
        try {
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option >= 0 && option <= books.size()){
                addBooks(books, option);
            } else {
                System.out.println("Invalid option");
                selectBooks(books);
            }
        } catch (Exception e){
            System.out.println("Invalid option");
            scanner.nextLine();
        }
    }

    public void searchBook() {
        // Get the data from the API
        DataBookResponse data = getData();
        List<Book> books = data.books().stream()
                .map(book -> new Book(book))
                .collect(Collectors.toList());

        // Save the books to the database
        selectBooks(books);
    }

    public void showBooks() {
        // Get all the books from the database
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);
    }


    public void showAuthors() {
        // Get all the authors from the database
        List<Author> authors = authorRepository.findAll();
        authors.forEach(System.out::println);
    }

    public void showAuthorsByYear() {
        // Get the authors by year
        System.out.println("Write the year");
        try {
            int year = scanner.nextInt();
            // Get the authors from the database
            List<Author> authors = authorRepository.findAuthorsByYear(year);
            if (authors.isEmpty()) {
                System.out.println("No authors found for the year " + year);
            } else {
                authors.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Invalid year");
            scanner.nextLine();
        }
    }

    public void showBooksByLanguage(){
        // Get the languages from the database
        List<String> languages = bookRepository.findLanguages();
        for (int i = 0; i < languages.size(); i++) {
            System.out.println(i + 1 + ". " + languages.get(i));
        }
        // Get the language from the user
        System.out.println("Write the number of the language");
        try {
            int option = scanner.nextInt();
            if (option >= 0 && option <= languages.size()){
                String language = languages.get(option - 1);
                // Get the books from the database
                List<Book> books = bookRepository.findByLanguagesContaining(language);
                books.forEach(System.out::println);
            } else {
                System.out.println("Invalid option");
                showBooksByLanguage();
            }
        } catch (Exception e){
            System.out.println("Invalid option");
            scanner.nextLine();
        }
    }
}
