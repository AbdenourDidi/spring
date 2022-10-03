package be.vinci.ipl.product.service;

import be.vinci.ipl.product.data.ProductsRepository;
import be.vinci.ipl.product.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {
        private final ProductsRepository repository;

        public ProductsService(ProductsRepository repository) {
            this.repository = repository;
        }

    /**
     * Creates a product in repository
     * @param product the video to create
     * @return true if the product was created, false if another product exists with same hash
     */
    public boolean createOne(Product product) {
        if (repository.existsById(product.getId())) return false;
        repository.save(product);
        return true;
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
    public Product readOne(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Updates a product in repository
     * @param newProduct the new values of the product
     * @return the old values of the product, or null if the product couldn't be found
     */
    public Product updateOne(Product newProduct) {
        Product oldProduct = readOne(newProduct.getId());
        if (oldProduct == null) return null;
        repository.save(newProduct);
        return oldProduct;
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
    public Product deleteOne(int id) {
        Product oldProduct = readOne(id);
        if (oldProduct == null) return null;
        repository.deleteById(id);
        return oldProduct;
    }
}
