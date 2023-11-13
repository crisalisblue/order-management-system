package crisalis.blue.models;

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
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Asset asset;

    @ManyToOne
    @JoinColumn(name="id_order")
    private Order idOrder;

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
        if(this.getId() != 0)
            itemDTO.setIdItem(this.getId());
        if(this.getAsset() != null) {
            if(this.getAsset().getId() != 0)
                itemDTO.setIdAsset(this.getAsset().getId());
            if(this.getAsset().getName() != null)
                itemDTO.setNameAsset(this.getAsset().getName());
        }
        if(this.getItemPrice()!=null &&this.getItemPrice().intValue() != 0)
            itemDTO.setItemPrice(this.getItemPrice());
        if(this.getItemDetails()!=null && !this.getItemDetails().isEmpty())
            itemDTO.setItemDitails(this.getItemDetails());
        if(this.getItemQuantity() != 0)
            itemDTO.setItemQuantity(this.getItemQuantity());
        if(this.getDiscountAmount()!=null && this.getDiscountAmount().intValue() != 0)
            itemDTO.setDiscountAmount(this.getDiscountAmount());
        if(this.getTotalPrice()!=null && this.getTotalPrice().intValue() != 0)
            itemDTO.setTotalPrice(this.getTotalPrice());
        if(this.getWarrantyYears() != 0)
            itemDTO.setWarrantyYears(this.getWarrantyYears());
        return itemDTO;
    }

}
