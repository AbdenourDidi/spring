package be.vinci.ipl.amazing.wishlists.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "wish")
public class Wish {
    @Id
    private int id;
    private String pseudo;
    private int productId;

    public NoIdWish removeId(){
        return new NoIdWish(pseudo, productId);
    }

}
