package be.vinci.ipl.amazing.wishlists.model;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class NoIdWish {
    private String pseudo;
    private long productId;

    public Wish toWish() {
        return new Wish(pseudo, productId);
    }
    public Wish toWish(long id) {
        return new Wish(id, pseudo, productId);
    }
}
