package crisalis.blue.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name ="Product")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product extends ExchangeGood {
    @Id
    @JoinColumn(name = "id")
    private Long id;
    @JoinColumn(name = "warranty")
    private Boolean garantia;
}