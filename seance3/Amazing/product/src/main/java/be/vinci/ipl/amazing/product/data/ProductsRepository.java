package be.vinci.ipl.amazing.product.data;

import be.vinci.ipl.amazing.product.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Product, Integer> {

}
