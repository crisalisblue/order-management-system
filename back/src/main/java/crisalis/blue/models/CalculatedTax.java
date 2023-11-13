// calculatedTaxed
package crisalis.blue.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import crisalis.blue.models.dto.CalculatedTaxDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculatedTax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    @JoinColumn(name="id_Asset")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Tax tax;
    @JoinColumn(name ="id_Order")
    @ManyToOne
    private Order order;
    private BigDecimal taxesAmount;

    /*public CalculatedTaxDTO calculatedTaxtoDTO()
    {
        CalculatedTaxDTO calculatedTaxDTO = new CalculatedTaxDTO();
        if(this.getId() != 0)
            calculatedTaxDTO.setId(this.getId());
        if(this.getIdTax() != null)
            calculatedTaxDTO.setIdTax(this.getIdTax());
        if(this.getIdOrder() != null)
            calculatedTaxDTO.setIdOrder(this.getIdOrder());
        if(this.getTaxesAmount()!=null && this.getTaxesAmount().intValue() != 0)
            calculatedTaxDTO.setTaxesAmount(this.getTaxesAmount());
        return calculatedTaxDTO;
    }*/
}