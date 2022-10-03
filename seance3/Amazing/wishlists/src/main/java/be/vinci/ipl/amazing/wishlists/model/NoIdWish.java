package be.vinci.ipl.amazing.wishlists.model;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class NoIdWish {
    private String pseudo;
    private int productId;

    public Wish toWish() {
        return new Wish(0, pseudo, productId);
    }
    public Wish toWish(int id) {
        return new Wish(id, pseudo, productId);
    }
}
