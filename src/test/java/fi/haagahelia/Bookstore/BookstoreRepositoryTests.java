package fi.haagahelia.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.domain.BookRepository;
import fi.haagahelia.Bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTests {

	@Autowired
    private BookRepository repository;
 
 @Test
    public void createNewBook() {
    	Book book1 = new Book("Harry Potter and the Philosopher's Stone", "JK Rowling", "1997", "11223344-5", 20.7, new Category("Fantasy"));
    	Book book2 = new Book("The Hunger Games", "Suzanne Collins", "2008", "55443322-1", 15.5, new Category("Young Adult"));
    	repository.save(book1);
    	repository.save(book2);
    	assertThat(book1.getId()).isNotNull();
    	assertThat(book2.getId()).isNotNull();
    }    
 
 @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("A Farewell to Arms");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("A Farewell to Arm");
 	}
}
