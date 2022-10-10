package be.vinci.ipl.amazing.wishlists.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WishExists409Exception extends ResponseStatusException {

    public WishExists409Exception(){
        super(HttpStatus.CONFLICT, "This user already wish this product");
    }

}
