package crisalis.blue.models;

import crisalis.blue.models.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name ="Product")
@Data
public class Product extends Asset {


    public ProductDTO productToDTO()
    {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(this.getName());
        productDTO.setBaseAmount(this.getBaseAmount());
        return productDTO;
    }

}