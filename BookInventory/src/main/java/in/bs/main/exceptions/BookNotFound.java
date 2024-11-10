package in.bs.main.exceptions;

public class BookNotFound  extends RuntimeException {
    public BookNotFound(String message) {
        super(message);
    }
}
