package be.vinci.ipl.amazing.wishlists.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WishNotFound404Exception extends ResponseStatusException {

    public WishNotFound404Exception(){
        super(HttpStatus.NOT_FOUND, "No wish could be found");
    }
}
