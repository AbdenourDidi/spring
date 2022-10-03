package be.vinci.ipl.amazing.product.controller;

import be.vinci.ipl.amazing.product.model.Product;
import be.vinci.ipl.amazing.product.service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Product> readOne(@PathVariable int id){
        Product product = service.readOne(id);
        if (product == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Create a product
     * @request POST /products
     * @body product to create
     * @response 209: product already exists, 201: returns create product
     */
    @PostMapping("/")
    public ResponseEntity<Product> createOne(@RequestBody Product product) {
        boolean created = service.createOne(product);
        if (!created) return new ResponseEntity<>(HttpStatus.CONFLICT);
        else return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    /**
     * Delete a product
     * @request DELETE /product/{id}
     * @response 404:product not found, 200:returns deleted product
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteOne(@PathVariable int id) {
        Product product = service.deleteOne(id);
        if (product == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(product, HttpStatus.OK);
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
    public ResponseEntity<Product> updateOne(@PathVariable int id, @RequestBody Product product) {
        if (product.getId() != id) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Product oldProduct = service.updateOne(product);
        if (oldProduct == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(oldProduct, HttpStatus.OK);
    }
}
