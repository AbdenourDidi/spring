package be.vinci.ipl.amazing.product.service;

import be.vinci.ipl.amazing.product.model.NoIdProduct;
import be.vinci.ipl.amazing.product.model.Product;
import be.vinci.ipl.amazing.product.data.ProductsRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {
        private final ProductsRepository repository;

        public ProductsService(ProductsRepository repository) {
            this.repository = repository;
        }

    /**
     * Creates a product in repository
     * @param product the product to create
     * @return The ID of the product created
     */
    public Product createOne(NoIdProduct product) {
        return repository.save(product.toProduct());
    }

    /**
     * Reads all products in repository
     * @return all products
     */
    public Iterable<Product> readAll() {
        return repository.findAll();
    }

    /**
     * Reads a product with a certain id from repository
     * @param id the id to search for
     * @return the product, or null if the product couldn't be found
     */
    public Product readOne(long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Updates a product
     * @param product New values of the product
     * @return True if the product could be updated, or false if the product couldn't be found
     */
    public boolean updateOne(Product product) {
        if (!repository.existsById(product.getId())) return false;
        repository.save(product);
        return true;
    }

    /**
     * Deletes all products from repository
     */
    public void deleteAll() {
        repository.deleteAll();
    }

    /**
     * Deletes a product with a certain id from repository
     * @param id the id of the product
     * @return the old values of the product, or null if the product couldn't be found
     */
    public boolean deleteOne(long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    /**
     * Reads all products of a category
     * @param category The category to search for
     * @return The list of all corresponding products
     */
    public Iterable<Product> readByCategory(String category) {
        return repository.findByCategory(category);
    }

    /**
     * Reads all products with a name corresponding to a query
     * @param name The name to search for
     * @return The list of all corresponding products
     */
    public Iterable<Product> readByName(String name) {
        return repository.findByNameContaining(name);
    }

    /**
     * Reads all products of a category with a name corresponding to a query
     * @param category The category to search for
     * @param name The name to search for
     * @return The list of all corresponding products
     */
    public Iterable<Product> readByCategoryAndName(String category, String name) {
        return repository.findByCategoryAndNameContaining(category, name);
    }
}
