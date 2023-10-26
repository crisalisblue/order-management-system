package crisalis.blue.models;


import crisalis.blue.models.dto.ItemsDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "exchange_good")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ExchangeGood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id ;
    @Column(name = "name")
    private String name;
    @Column(name = "mountBase")
    private double mountBase;

    public ItemsDTO toItemDTO()
    {
        return new ItemsDTO(this.getName(),this.getMountBase());
    }
}