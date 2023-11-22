package crisalis.blue.models;

import crisalis.blue.models.dto.AssetDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name ="Product")
@Data
@DiscriminatorValue("Product")
public class Product extends Asset {


    public Product(AssetDTO assetDTO)
    {
        this.setId(assetDTO.getId());
        this.setName(assetDTO.getName());
        this.setBaseAmount(assetDTO.getBaseAmount());
    }
    public Product(){}
}