package crisalis.blue.models;


import crisalis.blue.models.dto.ExchangeGoodDTO;
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
        if(!this.getName().equals(""))
        {
            if(this.getMountBase() !=0)
            {
                return true;
            }
        }
        return false;
    }

    public ExchangeGoodDTO toItemDTO()
    {
        return new ExchangeGoodDTO(this.getName(),this.getMountBase());
    }
}