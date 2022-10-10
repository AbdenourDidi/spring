package be.vinci.ipl.amazing.product.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import be.vinci.ipl.amazing.product.model.Product;
@Repository
public interface ProductsRepository extends CrudRepository<Product, Long> {
    Iterable<Product> findByCategory(String category);

    Iterable<Product> findByNameContaining(String name);

    Iterable<Product> findByCategoryAndNameContaining(String category, String name);
}
