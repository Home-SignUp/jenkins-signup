package com.addrbook.backend.dao;

import com.addrbook.backend.domain.Customer;
import com.addrbook.backend.domain.Person;
import com.addrbook.backend.domain.Product;
import com.addrbook.backend.exception.PersonNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.WebApplicationInitializer;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonDaoImpl implements PersonDao {
	
	private static final Logger logger = LoggerFactory.getLogger(WebApplicationInitializer.class);

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public PersonDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public Person findById(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);

		List<Person> list = jdbcTemplate.query("select * from person where id = :id", params, new PersonRowMapper());
		if (list.isEmpty()) {
			throw new PersonNotFoundException("No person found for id: " + id);
		} else {
			return list.get(0);
		}
	}

    /**
     *
     * @return List<Person>
     */
    public List<Person> getAllPersons(){
        List<Person> list = jdbcTemplate.query("select * from person", new PersonRowMapper());
        if (list.isEmpty()) {
            throw new PersonNotFoundException("No person found");
        } else {
            return list;
        }
    }

    public Customer getCustomer(){
        List<Customer> list = jdbcTemplate.query("select * from customers where customerNumber = 112", new CustomerRowMapper());
        if (list.isEmpty()) {
            throw new PersonNotFoundException("No person found for id: 112");
        } else {
            return list.get(0);
        }
    }
    public List<Customer> getAllCustomers(){
        List<Customer> list = jdbcTemplate.query("select * from customers", new CustomerRowMapper());
        if (list.isEmpty()) {
            throw new PersonNotFoundException("No person found");
        } else {
            return list;
        }
    }

    public Product findProductById(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        List<Product> list = jdbcTemplate.query("select * from products where id = :id", params, new ProductRowMapper());
        if (list.isEmpty()) {
            throw new PersonNotFoundException("No product found for id: " + id);
        } else {
            return list.get(0);
        }
    }

    public List<Product> getAllProducts(){
        List<Product> list = jdbcTemplate.query("select * from products", new ProductRowMapper());
        if (list.isEmpty()) {
            throw new PersonNotFoundException("No product found");
        } else {
            return list;
        }
    }

    public void update(Product product) {
        int numRowsAffected = jdbcTemplate.update(
                "update products set status = :status, name = :name, description = :description, price = :price, stock = :stock, packing = :packing where id = :id",
                new BeanPropertySqlParameterSource(product));

        if (numRowsAffected == 0) {
            throw new PersonNotFoundException("No product found for id: " + product.getId());
        }
    }

    public void insert(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        logger.debug("inserting product into database");

        jdbcTemplate.update(
                "insert into products (status, name, description, price, stock, packing, sku, mrp, category) values (:status, :name, :description, :price, :stock, :packing, :sku, :mrp, :category)",
                new BeanPropertySqlParameterSource(product), keyHolder);

        Integer newId = keyHolder.getKey().intValue();

        // populate the id
        product.setId(newId);
    }

    public void delete(Integer id) {
        String SQL = "DELETE FROM products WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", Integer.valueOf(id));
        jdbcTemplate.update(SQL, namedParameters);
        System.out.println("Deleted product into database with ID = " + id);
    }

	public void insert(Person person) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		logger.debug("inserting person into database");
		jdbcTemplate.update(
				"insert into person (user_name, first_name, last_name) values (:userName, :firstName, :lastName)",
				new BeanPropertySqlParameterSource(person), keyHolder);

		Integer newId = keyHolder.getKey().intValue();

		// populate the id
		person.setId(newId);
	}

	public void update(Person person) {
		int numRowsAffected = jdbcTemplate.update(
				"update person set user_name = :userName, first_name = :firstName, last_name = :lastName where id = :id",
				new BeanPropertySqlParameterSource(person));
		
		if (numRowsAffected == 0) {
			throw new PersonNotFoundException("No person found for id: " + person.getId());
		}
	}

	private static class PersonRowMapper implements RowMapper<Person> {
		public Person mapRow(ResultSet res, int rowNum) throws SQLException {
			Person p = new Person();
			p.setId(res.getInt("id"));
			p.setUserName(res.getString("user_name"));
			p.setFirstName(res.getString("first_name"));
			p.setLastName(res.getString("last_name"));
			return p;
		}
	}

    private static class CustomerRowMapper implements RowMapper<Customer> {
        public Customer mapRow(ResultSet res, int rowNum) throws SQLException {
            Customer c = new Customer();
            c.setCustomerNumber(res.getInt("customerNumber"));
            c.setCustomerName(res.getString("customerName"));
            c.setContactLastName(res.getString("contactLastName"));
            c.setContactFirstName(res.getString("contactFirstName"));
            c.setAddressLine1(res.getString("addressLine1"));
            c.setAddressLine2(res.getString("addressLine2"));
            c.setCity(res.getString("city"));
            c.setState(res.getString("state"));
            c.setPostalCode(res.getString("postalCode"));
            c.setCountry(res.getString("country"));
            c.setSalesRepEmployeeNumber(res.getInt("salesRepEmployeeNumber"));
            c.setCreditLimit(res.getDouble("creditLimit"));
            return c;
        }
    }

    private static class ProductRowMapper implements RowMapper<Product> {
        public Product mapRow(ResultSet res, int rowNum) throws SQLException {
            Product p = new Product();
            p.setId(res.getInt("id"));
            p.setSku(res.getInt("sku"));
            p.setName(res.getString("name"));
            p.setPrice(res.getDouble("price"));
            p.setMrp(res.getDouble("mrp"));
            p.setDescription(res.getString("description"));
            p.setPacking(res.getString("packing"));
            p.setImage(res.getString("image"));
            p.setCategory(res.getInt("category"));
            p.setStock(res.getInt("stock"));
            p.setStatus(res.getString("status"));
            return  p;
        }
    }

}
