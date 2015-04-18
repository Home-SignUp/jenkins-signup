package com.addrbook_backend.dao.fixture;

import com.addrbook_backend.domain.Person;

/**
 * Test fixture for unit tests.
 * 
 * @author Trey
 */
public class DaoImplTestFixture {

	/**
	 * Creates a Person with id=1, userName=thoward333, firstName=Trey, lastName=Howard
	 * @return
	 */
	public Person createTrey() {
		Person person = new Person();
		person.setId(1);
		person.setUserName("thoward333");
		person.setFirstName("Trey");
		person.setLastName("Howard");
		return person;
	}

}