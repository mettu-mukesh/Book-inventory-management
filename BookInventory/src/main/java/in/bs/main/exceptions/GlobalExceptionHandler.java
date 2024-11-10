package in.bs.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleBookNotFound(BookNotFound ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(AuthorNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleAuthorNotFound(AuthorNotFound ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(TitleNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTitleNotFound(TitleNotFound ex) {
        return ex.getMessage();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }



}
