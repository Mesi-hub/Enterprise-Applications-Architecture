package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	private ProductDAO productDAO;


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerDao.clearDB();
		productDAO.clearDB();

		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);

		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);

		Product product = new Product (1, "Laptop", 1250.00);
		Supplier supplier = new Supplier(1, "Dell Inc", "6252336788");
		product.setSupplier(supplier);
		productDAO.save(product);

		product = new Product (2, "Pen", 1.00);
		supplier = new Supplier(2, "BIC", "7252336787");
		product.setSupplier(supplier);
		productDAO.save(product);

		product = new Product (3, "Monitor", 110.00);
		supplier = new Supplier(3, "Sceptre", "5347778866");
		product.setSupplier(supplier);
		productDAO.save(product);

		System.out.println(customerDao.getCustomer(101));
		System.out.println(customerDao.getCustomer(66));

		System.out.println(productDAO.findProductByNumber(1));
		System.out.println(productDAO.findProductByNumber(2));
		System.out.println(productDAO.findProductByNumber(3));


		System.out.println("-----------All customers ----------------");
		System.out.println(customerDao.getAllCustomers());

		System.out.println("-----------All products ----------------");
		System.out.println(productDAO.getAllProducts());

		System.out.println("-----------Find by product name ----------------");
		System.out.println(productDAO.findByProductName("Laptop"));

		System.out.println("-----------Remove product ----------------");
		productDAO.removeProductByNumber(1);
	}
}
