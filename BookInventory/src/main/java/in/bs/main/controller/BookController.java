package in.bs.main.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.bs.main.dto.BookDTO;
import in.bs.main.dto.UserRegistrationDTO;
import in.bs.main.entities.User;
import in.bs.main.service.BookService;
import in.bs.main.service.UserService;

@CrossOrigin(origins = "http://localhost:63342")
@Controller
@RequestMapping("/books")
@Slf4j
@Tag(name = "StudentController",description = "It handle incoming HTTP requests," +                                  " manage interactions with services or databases, " +
        "and return appropriate responses to the client ")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;


    @Operation(summary = "Creating a Student ",
    description = "This operation is used to create Book")
    @ApiResponse(responseCode = "201",description = "Successfully created a student")
    @ApiResponse(responseCode = "500",description = "Internal server error")
    @PostMapping
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO bookDTO) {
        BookDTO createdBook = bookService.addBook(bookDTO);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }


    @Operation(summary = "Creating a User ",
    description = "This operation is used to register the User")
    @ApiResponse(responseCode = "201",description = "Successfully user registered")
    @ApiResponse(responseCode = "500",description = "Internal server error")
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        User registeredUser = userService.registerUser(userRegistrationDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    // Search by title
    @Operation(summary = "get the book  using tile of the book",
    description = "This operation is used to get the book using title ")
    @ApiResponse(responseCode = "200",description = "Successfully found the book")
    @ApiResponse(responseCode = "500",description = "Internal server error")
    @GetMapping("/search/title")
    public ResponseEntity<List<BookDTO>> searchByTitle(@Valid @RequestParam String title) {
        List<BookDTO> books = bookService.searchByTitle(title);
        return ResponseEntity.ok(books);
    }

    // Search by author
    @GetMapping("/search/author")
    @Operation(summary = "get the book  using author name",
    description = "This operation is used to get the book  using author name")
    @ApiResponse(responseCode = "200",description = "Successfully found the book")
    @ApiResponse(responseCode = "500",description = "Internal server error")
    public ResponseEntity<List<BookDTO>>searchByAuthor(@Valid @RequestParam String authorName) {
        List<BookDTO> books = bookService.searchByAuthor(authorName);
        return ResponseEntity.ok(books);
    }
    

    @GetMapping("/user")
    @Operation(summary = "get the user details",
    description = "This operation is used to get the details of user")
    @ApiResponse(responseCode = "200",description = "Successfully found the user ")
    @ApiResponse(responseCode = "500",description = "Internal server error")
    public String getUserByEmail(@RequestParam String email, Model model) {
    User user = userService.findByEmail(email);
    model.addAttribute("user", user);
    return "user-info";
}


    @Operation(summary = "delete the user ",
    description = "This operation is used to get the details of user")
    @ApiResponse(responseCode = "200",description = "Successfully deleted User ")
    @ApiResponse(responseCode = "500",description = "Internal server error")
    @DeleteMapping("/delete/user")
    public ResponseEntity<String> deleteUser(@RequestParam String username) {
        userService.deleteByUsername(username);
        return ResponseEntity.ok("User deleted successfully");
    }
}
