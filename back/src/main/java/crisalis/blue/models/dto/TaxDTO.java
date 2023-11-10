package crisalis.blue.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import crisalis.blue.models.CalculatedTax;
import crisalis.blue.models.Tax;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TaxDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("percentage")
    private BigDecimal percentage;

    @JsonProperty("baseAmount")
    private BigDecimal baseAmount;


    public Tax toTax()
    {
        Tax tax = new Tax();
        tax.setName(this.getName());
        tax.setId(this.getId());
        tax.setPercentage(this.getPercentage());
        tax.setBaseAmount(this.getBaseAmount());
        return tax;
    }
}
