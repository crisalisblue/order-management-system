package crisalis.blue.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import crisalis.blue.models.dto.CalculatedTaxDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="calculatedTax")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculatedTax {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @JsonProperty(value = "idTax")
    @Column(name = "idTax")
    private Long idTax;
    @JsonProperty(value = "idOrder")
    @Column(name = "idOrder")
    @ManyToOne
    private Order idOrder;
    @Column(name ="taxesAmount")
    private BigDecimal taxesAmount;

    public CalculatedTaxDTO CalculatedTaxToDTO()
    {
        return null;
    }
}
