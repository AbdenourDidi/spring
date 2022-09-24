package be.vinci.ipl.product.domaine;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private String category;
    private double price;
}
