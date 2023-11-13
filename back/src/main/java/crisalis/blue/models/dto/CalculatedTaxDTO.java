package crisalis.blue.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CalculatedTaxDTO {
    @JsonProperty(value = "calculatedTaxID")
    private Long calculatedTaxID;
    @JsonProperty(value = "taxID")
    private Long taxID;
    @JsonProperty(value = "taxName")
    private String taxName;
    @JsonProperty(value = "taxesAmount")
    private BigDecimal taxesAmount;
}
