package crisalis.blue.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import crisalis.blue.models.Item;
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
    @JsonProperty(value = "idAsset")
    private Long idAsset;
    @JsonProperty(value = "nameAsset")
    private String nameAsset;
    @JsonProperty(value = "orderDTO")
    private OrderDTO orderDTO;
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

    public Item toItem()
    {
        Item item = new Item();
        item.setId(this.getIdItem());
        item.setItemQuantity(this.getItemQuantity());
        item.setItemPrice(this.getItemPrice());
        item.setDiscountAmount(this.getDiscountAmount());
        item.setTotalPrice(this.getTotalPrice());
        item.setItemDetails(this.getItemDitails());
        item.setWarrantyYears(this.getWarrantyYears());

        return item;
    }
}
