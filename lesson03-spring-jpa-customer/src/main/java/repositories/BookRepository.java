package repositories;

import domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAll();

    void deleteById(int id);


    Optional<Book> findByIsbn(String isbn);

    @Query("update Book b set b.title = :title WHERE b.isbn = :isbn")
    Optional<Book> setBookTitleByIsbn(@Param("isbn") String isbn, @Param("title") String title);
}


