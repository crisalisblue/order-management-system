package crisalis.blue.models;

import crisalis.blue.models.dto.TaxDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Tax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal percentage;
    private BigDecimal baseAmount;
    public TaxDTO toDTO(){
        TaxDTO taxDTO = new TaxDTO();
        if(this.getId() != 0)
            taxDTO.setId(this.getId());
        if(this.getName()!= null && !this.getName().isEmpty())
            taxDTO.setName(this.getName());
        if(this.getBaseAmount()!=null && this.getBaseAmount().intValue()!=0)
            taxDTO.setBaseAmount(this.getBaseAmount());
        if(this.getPercentage()!=null && this.getPercentage().intValue() !=0)
            taxDTO.setPercentage(this.getPercentage());
        return taxDTO;
    }

    //Falta agregar relacion con otras tablas...
}
