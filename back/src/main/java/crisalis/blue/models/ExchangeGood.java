package crisalis.blue.models;


import crisalis.blue.models.dto.ExchangeGoodDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "exchange_good")
@Inheritance(strategy = InheritanceType.JOINED)
@Data

public  class ExchangeGood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id ;
    @Column(name = "name")
    private String name;
    @Column(name = "mountBase")
    private double mountBase;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinTable(
            name = "tax_exchangeGood",
            joinColumns = @JoinColumn(name="exchangeGood_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="tax_id",referencedColumnName = "id")
    )
    private List<Tax> taxList;
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
