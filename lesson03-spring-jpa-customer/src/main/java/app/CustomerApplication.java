package app;

import dao.BookDao;
import domain.Book;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.BookRepository;
import repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain")
public class CustomerApplication implements CommandLineRunner {

    @Autowired
    CustomerRepository customerrepository;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookDao bookDao;

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        customerrepository.save(new Customer("Jack", "Bauer", "jack@acme.com"));
        customerrepository.save(new Customer("Chloe", "O'Brian", "chloe@acme.com"));
        customerrepository.save(new Customer("Kim", "Bauer", "kim@acme.com"));
        customerrepository.save(new Customer("David", "Palmer", "dpalmer@gmail.com"));
        customerrepository.save(new Customer("Michelle", "Dessler", "mich@hotmail.com"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : customerrepository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer by ID
        Optional<Customer> custOpt = customerrepository.findById(1L);
        Customer customer = custOpt.get();
        System.out.println("Customer found with findOne(1L):");
        System.out.println("--------------------------------");
        System.out.println(customer);
        System.out.println();

        //create and save 3 books in the db
        bookRepository.save(new Book(111, "Enterprise Architecture", "9876456", "Abebe", 150.00));
        bookRepository.save(new Book(112, "WAA", "3476422", "Dean", 140.00));
        bookRepository.save(new Book(113, "MPP", "2345678", "John", 122.50));

        // retrieve all books
        System.out.println("Books found with findAll():");
        System.out.println("-------------------------------");
        for (Book book : bookRepository.findAll()) {
            System.out.println(book);
        }
        System.out.println();



        //TODO
        // update a book (update a book with ISBN = '9876456' and set title to 'Introduction to Java')
        Optional<Book> bookToUpdateOpt = bookRepository.setBookTitleByIsbn("9876456", "Introduction to Java");
        if (bookToUpdateOpt.isPresent()) {
            Book bookToUpdate = bookToUpdateOpt.get();
            bookToUpdate.setTitle("Introduction to Java");
            bookRepository.save(bookToUpdate);
        }


        //Delete book with id = 2
        bookRepository.deleteById(2);

        // retrieve all books after CRUD operations
        System.out.println("Books found with findAll():");
        System.out.println("-------------------------------");
        for (Book book : bookRepository.findAll()) {
            System.out.println(book);
        }
        System.out.println();

    }
}
