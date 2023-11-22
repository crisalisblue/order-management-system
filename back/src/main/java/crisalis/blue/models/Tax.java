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
        taxDTO.setId(this.getId());
        taxDTO.setName(this.getName());
        taxDTO.setBaseAmount(this.getBaseAmount());
        taxDTO.setPercentage(this.getPercentage());
        return taxDTO;
    }
    public Tax(TaxDTO taxDTO)
    {
        this.setName(taxDTO.getName());
        this.setPercentage(taxDTO.getPercentage());
        this.setBaseAmount(taxDTO.getBaseAmount());
    }

    //Falta agregar relacion con otras tablas...
}
