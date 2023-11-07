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
    @Column(name = "id_Item")
    private Long id;

    // Id de pedido
    @JoinColumn(name = "id_Order",referencedColumnName = "id_Order")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private Order order;

    // Id de de bien de cambio
    @JoinColumn(name="id_Asset")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private Asset asset;

    // monto Item
    @Column(name = "itemPrice")
    private BigDecimal itemPrice;
    // Descripción de items
    @Column(name= "itemDetails")
    private String itemDetails;
    // Cantidad
    @Column(name = "itemQuantity" )
    private int itemQuantity;
    // monto de descuento
    @Column(name = "discountAmount")
    private BigDecimal discountAmount;
    // Precio total de la venta
    @Column(name="totalPrice")
    private BigDecimal totalPrice;
    // Impuestos Aplicados
    @Column(name = "warranty_years")
    private int warrantyYears;

    public ItemDTO toItemDTO()
    {
        ItemDTO itemDTO = new ItemDTO();
        if(this.getId() != 0)
            itemDTO.setIdItem(this.getId());
        if(this.getOrder() != null) {
            if(this.getOrder().getId() != 0)
                itemDTO.setIdOrder(this.getOrder().getId());
        }
        if(this.getAsset() != null) {
            if(this.getAsset().getId() != 0)
                itemDTO.setIdAsset(this.getAsset().getId());
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
