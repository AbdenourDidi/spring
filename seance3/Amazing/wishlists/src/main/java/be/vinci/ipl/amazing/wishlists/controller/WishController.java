package be.vinci.ipl.amazing.wishlists.controller;

import be.vinci.ipl.amazing.wishlists.exceptions.WishNotFound404Exception;
import be.vinci.ipl.amazing.wishlists.model.NoIdWish;
import be.vinci.ipl.amazing.wishlists.service.WishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/{pseudo}")
    public Iterable<NoIdWish> readFromUser(@PathVariable String pseudo) {
        return service.readFromUser(pseudo);
    }

}
