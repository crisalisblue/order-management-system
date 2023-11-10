package crisalis.blue.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import crisalis.blue.models.Order;
import crisalis.blue.models.Tax;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculatedTaxDTO {
    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "idTax")
    private List<Tax> idTax;
    @JsonProperty(value = "idOrder")
    private List<Order> idOrder;
    @JsonProperty(value = "taxesAmount")
    private BigDecimal taxesAmount;
}