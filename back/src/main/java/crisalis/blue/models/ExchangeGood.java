package crisalis.blue.models;


import crisalis.blue.models.dto.ItemsDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "exchange_good")
@Inheritance(strategy = InheritanceType.JOINED)
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

    public boolean checkEmpty()
    {
        if(!this.getName().isEmpty())
        {
            if(this.getMountBase() !=0)
            {
                return true;
            }
        }
        return false;
    }

    public ItemsDTO toItemDTO()
    {
        return new ItemsDTO(this.getName(),this.getMountBase());
    }
}