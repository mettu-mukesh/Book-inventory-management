package in.bs.main;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Book Inventory Management System",
		contact = @Contact(email = "mukeshmick8@gmail.com", name = "Mukesh"),
		description = "A system to manage and track books in an inventory, including details like " +
				"availability, ISBN, author, and genre. It streamlines book management " +
				"for libraries, bookstores, and other institutions."
))
public class BookInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookInventoryApplication.class, args);
	}
}
