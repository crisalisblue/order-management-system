// calculatedTaxed
package crisalis.blue.models;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.dto.CalculatedTaxDTO;
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
    @JoinColumn(name="id_Tax")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tax tax;
    @JoinColumn(name ="id_Order")
    @ManyToOne
    private Order order;
    private BigDecimal taxesAmount;

    public crisalis.blue.models.dto.CalculatedTaxDTO calculatedTaxToDTO()
    {
            crisalis.blue.models.dto.CalculatedTaxDTO calculatedTaxDTO = new crisalis.blue.models.dto.CalculatedTaxDTO();
            calculatedTaxDTO.setCalculatedTaxID(this.getId());
            calculatedTaxDTO.setTaxID(this.getTax().getId());
            calculatedTaxDTO.setTaxName(this.getTax().getName());
            calculatedTaxDTO.setTaxesAmount(this.getTaxesAmount());
            return calculatedTaxDTO;
    }
    public CalculatedTax(CalculatedTaxDTO calculatedTaxDTO)
    {
        this.setTaxesAmount(calculatedTaxDTO.getTaxesAmount());
    }
}