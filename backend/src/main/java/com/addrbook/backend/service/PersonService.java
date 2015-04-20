package com.addrbook.backend.service;

import com.addrbook.backend.domain.Customer;
import com.addrbook.backend.domain.Person;
import com.addrbook.backend.domain.Product;

import java.util.List;

/**
 * Service for managing people.
 * 
 * @author Trey
 */
public interface PersonService {

	/**
	 * @param id
	 * @return Returns the person with the given id.
	 */
	public Person getPersonById(Integer id);

    public List<Person> getAllPersons();

    public Customer getCustomer();

    public List<Customer> getAllCustomers();

    public Product getProductById(Integer id);

    public List<Product> getAllProducts();

	/**
	 * Creates a new Person and populates the <code>id</code> property with the generated id.
	 * @param person All non-id properties are used to create the new person.
	 */
	public void savePerson(Person person);
	
//	/**
//	 * @param person The person to be updated.
//	 */
	public void updatePerson(Person person);

    public void saveProduct(Product p);

    public void updateProduct(Product p);

    public void deleteProduct(Integer id);

}
