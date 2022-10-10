package be.vinci.ipl.amazing.product.controller;

import be.vinci.ipl.amazing.product.model.NoIdProduct;
import be.vinci.ipl.amazing.product.model.Product;
import be.vinci.ipl.amazing.product.service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService service;

    public ProductsController(ProductsService service) {
        this.service = service;
    }

    /**
     * Read all product
     * @request GET/products
     * @response 200:returns all products
     */
    @GetMapping("/")
    public Iterable<Product> readAll(){
        return service.readAll();
    }

    /**
     * Read one product
     * @request GET/products/{id}
     * @response 404:product not found, 200:returns one product
     */
    @GetMapping("/{id}")
    public Product readOne(@PathVariable long id){
        Product product = service.readOne(id);
        if (product == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return product;
    }

    /**
     * Create a product
     * @request POST /products
     * @body product to create
     * @response 209: product already exists, 201: returns create product
     */
    @PostMapping("/")
    public ResponseEntity<Product> createOne(@RequestBody NoIdProduct product) {
        if (product.getCategory() == null || product.getName() == null || product.getPrice() <=0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Product created = service.createOne(product);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Delete a product
     * @request DELETE /product/{id}
     * @response 404:product not found, 200:returns deleted product
     */
    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable long id) {
        boolean found = service.deleteOne(id);
        if (!found) new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete all products
     * @request DELETE /products
     * @response 200: all products are deleted
     */
    @DeleteMapping("/")
    public void deleteAll() {
        service.deleteAll();
    }

    /**
     * Update a product
     * @request PUT /product/{id}
     * @body new value of the product
     * @response 400: product does not match id, 404: product not found, 200: returns old value of product
     */
    @PutMapping("/{id}")
    public void updateOne(@PathVariable long id, @RequestBody Product product) {
        if (product.getId() != id || product.getName() == null || product.getCategory() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        boolean found = service.updateOne(product);
        if (!found) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
