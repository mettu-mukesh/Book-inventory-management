package in.bs.main.service;


import in.bs.main.dto.*;
import in.bs.main.entities.*;
import in.bs.main.exceptions.AuthorNotFound;
import in.bs.main.exceptions.TitleNotFound;
import in.bs.main.mapper.BookMappers;
import in.bs.main.repositories.AuthorRepository;
import in.bs.main.repositories.BookRepository;
import in.bs.main.repositories.GenreRepository;
import in.bs.main.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    public BookDTO addBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());

        // Check if the author already exists or create a new one
        if (bookDTO.getAuthor() != null) {
            Author author = authorRepository.findByName(bookDTO.getAuthor().getName());
            if (author == null) {
                author = new Author();
                author.setName(bookDTO.getAuthor().getName());
                authorRepository.save(author);
            }
            book.setAuthor(author);
        }

        // Check if the publisher already exists or create a new one
        if (bookDTO.getPublisher() != null) {
            Publisher publisher = publisherRepository.findByName(bookDTO.getPublisher().getName());
            if (publisher == null) {
                publisher = new Publisher();
                publisher.setName(bookDTO.getPublisher().getName());
                publisher.setDate(bookDTO.getPublisher().getDate()); // Set the date from PublisherDTO
                publisherRepository.save(publisher);
            } else {
                publisher.setDate(bookDTO.getPublisher().getDate()); // Update the date if publisher exists
            }
            book.setPublisher(publisher);
        }
        // Handle BookDetails if included in the DTO
        if (bookDTO.getBookDetails() != null) {
            BookDetails bookDetails = new BookDetails();
            bookDetails.setDescription(bookDTO.getBookDetails().getDescription());
            bookDetails.setPages(bookDTO.getBookDetails().getPages());
            bookDetails.setBook(book);

            book.setBookDetails(bookDetails);
        }

        // Handle genres if included in the DTO
        if (bookDTO.getGenres() != null) {
            List<Genre> genres = bookDTO.getGenres().stream().map(genreDTO -> {
                Genre genre = genreRepository.findByName(genreDTO.getName());
                if (genre == null) {
                    genre = new Genre();
                    genre.setName(genreDTO.getName());
                    genreRepository.save(genre);
                }
                return genre;
            }).collect(Collectors.toList());
            book.setGenres(new HashSet<>(genres));
        }

        // Save the book and return the mapped DTO
        book = bookRepository.save(book);
        return BookMappers.toDTO(book);
    }

    public List<BookDTO> searchByTitle(String title) {
        List<Book> books = bookRepository.findByTitleContaining(title);
        if (books.isEmpty()) {
            throw new TitleNotFound("No books found with title: " + title);
        }
        return books.stream().map(BookMappers::toDTO).collect(Collectors.toList());
    }

    public List<BookDTO> searchByAuthor(String authorName) {
        List<Book> books = bookRepository.findByAuthor_NameContaining(authorName);
        if (books.isEmpty()) {
            throw new AuthorNotFound("No books found by author: " + authorName);
        }
        return books.stream().map(BookMappers::toDTO).collect(Collectors.toList());
    }
}

