package be.vinci.ipl.amazing.wishlists.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "wish")
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String pseudo;
    @Column(name = "product_id")
    private long productId;

    public Wish(String pseudo, long productId) {
        this.id = 0L;
        this.pseudo = pseudo;
        this.productId = productId;
    }

    public NoIdWish removeId(){
        return new NoIdWish(pseudo, productId);
    }

}
