package be.vinci.ipl.amazing.wishlists.controller;

import be.vinci.ipl.amazing.wishlists.exceptions.WishNotFound404Exception;
import be.vinci.ipl.amazing.wishlists.model.NoIdWish;
import be.vinci.ipl.amazing.wishlists.model.Product;
import be.vinci.ipl.amazing.wishlists.service.WishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/wishs")

public class WishController {
    private final WishService service;


    public WishController(WishService service) {
        this.service = service;
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateOne(@RequestBody NoIdWish noIdWish) throws WishNotFound404Exception{
        service.updateOne(noIdWish);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/")
    public void deleteOne(@RequestBody NoIdWish noIdWish) throws WishNotFound404Exception {
        service.deleteOne(noIdWish);
    }

    @PutMapping("/")
    public void addWish(@RequestBody NoIdWish noIdWish) {
        boolean created = service.addWish(noIdWish.getPseudo(), noIdWish.getProductId());
        if (!created) throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    @GetMapping("/user/{pseudo}")
    public Iterable<Product> readFromUser(@PathVariable String pseudo) {
        return service.readFromUser(pseudo);
    }

}
