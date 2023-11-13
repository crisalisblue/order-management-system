package crisalis.blue.models;

import crisalis.blue.models.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends ExchangeGood {
    @Column(name = "warranty")
    private String warranty;

    public ProductDTO productToDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(this.getName());
        productDTO.setMountBase(this.getMountBase());
        productDTO.setGarantia(this.warranty);
        return productDTO;
    }

}