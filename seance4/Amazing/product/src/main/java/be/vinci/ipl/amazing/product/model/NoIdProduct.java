package be.vinci.ipl.amazing.product.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoIdProduct {
    private String name;
    private String category;
    private double price;
    public Product toProduct() {
        return new Product(0L, name, category, price);
    }
}
