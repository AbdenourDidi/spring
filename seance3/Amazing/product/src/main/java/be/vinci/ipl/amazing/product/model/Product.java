package be.vinci.ipl.amazing.product.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "products")
public class Product {
    @Id
    private int id;
    private String name;
    private String category;
    private double price;
}
