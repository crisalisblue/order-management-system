package crisalis.blue.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class TaxDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("percentage")
    private BigDecimal percentage;

    @JsonProperty("baseAmount")
    private BigDecimal baseAmount;
    @JsonProperty("assets")
    private List<Long> assetList;
    @JsonProperty("calculatedTax")
    private List<Long> calculatedTaxes;

    public TaxDTO()
    {
        assetList = new ArrayList<>();
        calculatedTaxes = new ArrayList<>();
    }
}
