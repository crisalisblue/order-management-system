package crisalis.blue.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Product")
@Data
@DiscriminatorValue("Product")
public class Product extends Asset {

    public ProductDTO productToDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(this.getId());
        productDTO.setName(this.getName());
        productDTO.setMountBase(this.getMountBase());
        productDTO.setGarantia(this.warranty);
        return productDTO;
    }

}