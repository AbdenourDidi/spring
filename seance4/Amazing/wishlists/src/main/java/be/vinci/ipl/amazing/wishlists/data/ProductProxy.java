package be.vinci.ipl.amazing.wishlists.data;

import be.vinci.ipl.amazing.wishlists.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductProxy {

    @GetMapping("/products/{id}")
    Product readOne(@PathVariable long id);
}
