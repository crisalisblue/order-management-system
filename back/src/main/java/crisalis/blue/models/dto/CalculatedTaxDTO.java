package crisalis.blue.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculatedTaxDTO {
    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "idTax")
    private Long idTax;
    @JsonProperty(value = "idOrder")
    private Long idOrder;
    @JsonProperty(value = "taxesAmount")
    private BigDecimal taxesAmount;
}
