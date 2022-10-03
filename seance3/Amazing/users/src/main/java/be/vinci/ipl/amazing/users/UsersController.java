package be.vinci.ipl.amazing.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UsersController {

    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }

    @PostMapping("/users/{pseudo}")
    public void createOne(@PathVariable String pseudo, @RequestBody User user) {
        if (user.getPseudo() == null || !user.getPseudo().equals(pseudo) ||
                user.getLastname() == null || user.getFirstname() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        boolean created = service.createOne(user);
        if (!created) throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    @GetMapping("/users/{pseudo}")
    public User readOne(@PathVariable String pseudo) {
        User user = service.readOne(pseudo);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return user;
    }

    @PutMapping("/users/{pseudo}")
    public void updateOne(@PathVariable String pseudo, @RequestBody User user) {
        if (user.getPseudo() == null || !user.getPseudo().equals(pseudo) ||
                user.getLastname() == null || user.getFirstname() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        boolean found = service.updateOne(user);
        if (!found) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/users/{pseudo}")
    public void deleteOne(@PathVariable String pseudo) {
        boolean found = service.deleteOne(pseudo);
        if (!found) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
