package be.vinci.ipl.amazing.wishlists.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "products")
public class Product {
    @Id
    private long id;
    private String name;
    private String category;
    private double price;
}
