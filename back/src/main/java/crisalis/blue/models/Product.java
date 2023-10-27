package crisalis.blue.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

@Entity(name ="Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends ExchangeGood {
    @Column(name = "warranty")
    private Boolean garantia;
}