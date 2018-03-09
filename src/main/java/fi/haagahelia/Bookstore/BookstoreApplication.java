package fi.haagahelia.Bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.domain.BookRepository;
import fi.haagahelia.Bookstore.domain.Category;
import fi.haagahelia.Bookstore.domain.CategoryRepository;



@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository catrepository) {
		return (args) -> {
			catrepository.save(new Category("Classics"));
			catrepository.save(new Category("Non-Fiction"));
			catrepository.save(new Category("Fantasy"));
			catrepository.save(new Category("Romance"));
			catrepository.save(new Category("Fiction"));
			
			
			Book book1 = new Book("A Farewell to Arms", "Ernest Hemingway", "1929", "1232323-21", 10.5, catrepository.findByCategoryName("Classics").get(0));
			Book book2 = new Book("Animal Farm", "George Orwell", "1945", "2212343-5", 12.52, catrepository.findByCategoryName("Classics").get(0));
			repository.save(book1);
			repository.save(book2);
		};
	}
}
