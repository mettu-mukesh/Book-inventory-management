package in.bs.main.mapper;

import in.bs.main.dto.*;
import in.bs.main.entities.*;

import java.util.stream.Collectors;

public class BookMappers {

    public static BookDTO toDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());

        // Author mapping
        if (book.getAuthor() != null) {
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setName(book.getAuthor().getName());
            bookDTO.setAuthor(authorDTO);
        }

        // Publisher mapping
        if (book.getPublisher() != null) {
            PublisherDTO publisherDTO = new PublisherDTO();
            publisherDTO.setName(book.getPublisher().getName());
            bookDTO.setPublisher(publisherDTO);
        }

        // Book details mapping
        if (book.getBookDetails() != null) {
            BookDetailsDTO bookDetailsDTO = new BookDetailsDTO();
            bookDetailsDTO.setDescription(book.getBookDetails().getDescription());
            bookDetailsDTO.setPages(book.getBookDetails().getPages());
            bookDTO.setBookDetails(bookDetailsDTO);
        }

        // Genres mapping
        if (book.getGenres() != null) {
            bookDTO.setGenres(book.getGenres().stream().map(genre -> {
                GenreDTO genreDTO = new GenreDTO();
                genreDTO.setName(genre.getName());
                return genreDTO;
            }).collect(Collectors.toList()));
        }

        return bookDTO;
    }

}
