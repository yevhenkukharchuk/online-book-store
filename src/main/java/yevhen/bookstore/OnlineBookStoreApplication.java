package yevhen.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import yevhen.bookstore.model.Book;
import yevhen.bookstore.service.BookService;

import java.math.BigDecimal;

@SpringBootApplication
public class OnlineBookStoreApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setAuthor("Author1");
            book.setTitle("Book1");
            book.setPrice(BigDecimal.valueOf(5.99));
            book.setIsbn("123456789");
            book.setDescription("Good book");
            book.setCoverImage("Image1.jpg");
            bookService.save(book);
        };
    }
}
