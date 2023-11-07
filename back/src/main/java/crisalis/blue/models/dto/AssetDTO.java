package crisalis.blue.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor

public class AssetDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("baseAmount")
    private BigDecimal baseAmount;
    @JsonProperty("taxList")
    private List<Long> taxDTOList;
    @JsonProperty("type")
    private String type;
    @JsonProperty("supportFree")
    private BigDecimal supportFree;
    public AssetDTO()
    {
        taxDTOList = new ArrayList<Long>();
    }


}