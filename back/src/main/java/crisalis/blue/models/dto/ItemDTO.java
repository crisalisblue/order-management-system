package crisalis.blue.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    @JsonProperty(value = "id")
    private Long idItem;
    @JsonProperty(value = "idOrder")
    private Long idOrder;
    @JsonProperty(value = "idAsset")
    private Long idAsset;
    @JsonProperty(value = "itemPrice")
    private BigDecimal itemPrice;
    @JsonProperty(value = "itemDitails")
    private String itemDitails;
    @JsonProperty(value = "itemQuantity")
    private int itemQuantity;
    @JsonProperty(value = "discountAmount")
    private BigDecimal discountAmount;
    @JsonProperty(value = "totalPrice")
    private BigDecimal totalPrice;
    @JsonProperty(value = "warrantyYears")
    private int warrantyYears;
}
