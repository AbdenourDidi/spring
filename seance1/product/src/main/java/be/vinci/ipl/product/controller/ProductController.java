package be.vinci.ipl.product.controller;

import be.vinci.ipl.product.domaine.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final List<Product> products = new ArrayList<>();
    static{
        products.add(new Product(1, "salade", "alimentation", 2.50));
    }

    /**
     * Read all product
     * @request GET/products
     * @response 200:returns all products
     */
    @GetMapping("/")
    public Iterable<Product> readAll(){
        return products;
    }

    /**
     * Read one product
     * @request GET/products/{id}
     * @response 404:product not found, 200:returns one product
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> readOne(@PathVariable int id){
        int index = findIndex(id);
        if(index == -1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Product product = products.get(index);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Create a product
     * @request POST /videos
     * @body video to create
     * @response 209: video already exists, 201: returns create video
     */
    @PostMapping("/")
    public ResponseEntity<Product> createOne(@RequestBody Product product) {
        if (exists(product.getId())) return new ResponseEntity<>(HttpStatus.CONFLICT);
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    /**
     * Delete a product
     * @request DELETE /product/{id}
     * @response 404:product not found, 200:returns deleted product
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteOne(@PathVariable int id) {
        int index = findIndex(id);
        if(index == -1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Product product = products.remove(index);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }



    /**
     * find a product
     * @param id product id
     * @return true if the product exists, false otherwise
     */
    public boolean exists(int id){
        for (Product p: products) {
            if (p.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * find a index for product
     * @param id product id
     * @return index if the product exists, -1 otherwise
     */
    public int findIndex(int id){
        int index = 0;
        for (Product p: products) {
            if (p.getId() == id) {
                return index;
            }
            index++;
        }
        return -1;
    }

}
