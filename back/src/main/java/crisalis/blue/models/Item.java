package crisalis.blue.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import crisalis.blue.models.dto.ItemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "Item")
@Table(name = "Item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    // Id Bien de cambio-Pedido
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long id;

    // Id de de bien de cambio
    @JoinColumn(name="id_Asset")
    @ManyToOne(fetch = FetchType.LAZY)
    private Asset asset;

    @ManyToOne
    @JoinColumn(name="id_order")
    @JsonProperty(value = "idOrder")
    private Order order;

    // monto Item
    private BigDecimal itemPrice;
    // Descripción de items

    private String itemDetails;
    // Cantidad

    private int itemQuantity;
    // monto de descuento

    private BigDecimal discountAmount;
    // Precio total de la venta

    private BigDecimal totalPrice;
    // Impuestos Aplicados

    private int warrantyYears;

    public ItemDTO toItemDTO()
    {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setIdItem(this.getId());
        itemDTO.setIdAsset(this.getAsset().getId());
        itemDTO.setNameAsset(this.getAsset().getName());
        itemDTO.setItemPrice(this.getItemPrice());
        itemDTO.setItemDitails(this.getItemDetails());
        itemDTO.setItemQuantity(this.getItemQuantity());
        itemDTO.setDiscountAmount(this.getDiscountAmount());
        itemDTO.setTotalPrice(this.getTotalPrice());
        itemDTO.setWarrantyYears(this.getWarrantyYears());
        return itemDTO;
    }
    public Item (ItemDTO itemDTO) {
        this.setDiscountAmount(itemDTO.getDiscountAmount());
        this.setItemPrice(itemDTO.getItemPrice());
        this.setItemQuantity(itemDTO.getItemQuantity());
        this.setItemDetails(itemDTO.getItemDitails());
        this.setWarrantyYears(itemDTO.getWarrantyYears());
        this.setTotalPrice(itemDTO.getTotalPrice());

    }
}
