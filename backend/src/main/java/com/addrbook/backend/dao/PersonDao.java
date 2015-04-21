package com.addrbook.backend.dao;

import com.addrbook.backend.domain.Product;
import com.addrbook.backend.domain.TomcatUsers;

import java.util.List;

/**
 * DAO for person database table.
 * 
 * @author Trey
 */
public interface PersonDao {

	/**
	 * Selects the Product record with the given id.
	 * @param id
	 * @return Returns the Product for the given id. Returns null if none found.
	 */
    Product findProductById(Integer id);

    public List<Product> getAllProducts();

    /**
     * Inserts a person record using the non-id properties.
     * The id property is populated with the generated id.
     * @param p
     */
    public void insert(Product p);

    /**
     * Updates the Product record with p.getId().
     * @param p
     */
    public void update(Product p);

    public void delete(Integer id);


    /**
     * Selects the Product record with the given id.
     * @param userName
     * @return Returns the Product for the given id. Returns null if none found.
     */
    TomcatUsers findUserById(String userName);

    public List<TomcatUsers> getAllUsers();

    /**
     * Inserts a person record using the non-id properties.
     * The id property is populated with the generated id.
     * @param u
     */
    public void insert(TomcatUsers u);

    /**
     * Updates the Product record with p.getId().
     * @param u
     */
    public void update(TomcatUsers u);

    public void delete(String userName);
}