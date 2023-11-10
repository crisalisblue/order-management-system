package crisalis.blue.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name ="Product")
@Data
@DiscriminatorValue("Product")
public class Product extends Asset {



}