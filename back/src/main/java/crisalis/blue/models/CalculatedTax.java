// calculatedTaxed
package crisalis.blue.models;

import crisalis.blue.exceptions.custom.EmptyElementException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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

    public crisalis.blue.models.dto.CalculatedTaxDTO calculatedTaxToDTO()
    {
        if(checkCalculatedTax()) {
            crisalis.blue.models.dto.CalculatedTaxDTO calculatedTaxDTO = new crisalis.blue.models.dto.CalculatedTaxDTO();
            calculatedTaxDTO.setCalculatedTaxID(this.getId());
            calculatedTaxDTO.setTaxID(this.getTax().getId());
            calculatedTaxDTO.setTaxName(this.getTax().getName());
            calculatedTaxDTO.setTaxesAmount(this.getTaxesAmount());
            return calculatedTaxDTO;
        }
        else throw new EmptyElementException("Los parámetros son nulos ");
    }
    private boolean checkCalculatedTax()
    {
        if(this.getId() != null && this.getId() != 0)
        {
            if (this.getTax() != null && this.getTax().getId() != 0) {
                if (this.getTax().getName() != null)
                {
                    if (this.getTaxesAmount() != null && !BigDecimal.ZERO.equals(this.getTaxesAmount())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}