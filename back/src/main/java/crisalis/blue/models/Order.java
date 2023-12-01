package crisalis.blue.models;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.dto.CalculatedTaxDTO;
import crisalis.blue.models.dto.ItemDTO;
import crisalis.blue.models.dto.OrderDTO;
import crisalis.blue.repositories.ItemRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;
    @Column(name = "totalDiscount")
    private BigDecimal totalDiscount;
    @Temporal(TemporalType.DATE)
    @Column(name = "datesOrder")
    private Date datesOrder;
    @Column(name = "active")
    private boolean active;
    @Column(name = "totalPrice")
    private BigDecimal totalPrice;
    @Column(name = "subTotal")
    private BigDecimal subTotal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    private List<Item> items;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    private List<CalculatedTax> calculatedTaxes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "suscriptionAsset_id", referencedColumnName = "id")
    private Asset assetSuscription;

    public OrderDTO toOrderDTO() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setIdOrder(this.getId());
        orderDTO.setDateOrder(this.getDatesOrder());
        orderDTO.setTotalDiscount(this.getTotalDiscount());
        orderDTO.setTotalPrice(this.getTotalPrice());
        if(this.getCustomer() != null) {
            orderDTO.setCustomerID(this.getCustomer().getId());
            orderDTO.setCustomerName(this.getCustomer().getName());
        }
        orderDTO.setSubTotal(this.getSubTotal());
        orderDTO.setActive(this.active);
        if(this.getItems() != null) {
            orderDTO.setItemDTO(this.getItems().stream().map(Item::toItemDTO).collect(Collectors.toList()));
        }
        orderDTO.setSubTotal(this.getSubTotal());
        orderDTO.setActive(this.active);

        if (this.getCalculatedTaxes() != null) {
            orderDTO.setCalculatedTaxDTOS(
                    this.getCalculatedTaxes().stream().map(CalculatedTax::calculatedTaxToDTO)
                            .collect(Collectors.toList()));
        }
        return orderDTO;
    }

    public Order(OrderDTO orderDTO) {
        this.setId(orderDTO.getIdOrder());
        this.setDatesOrder(orderDTO.getDateOrder());
        this.setSubTotal(orderDTO.getSubTotal());
        this.setActive(orderDTO.getActive());
        this.setTotalDiscount(orderDTO.getTotalDiscount());
        this.setTotalPrice(orderDTO.getTotalPrice());
        this.setItems(createListItemDeItemDTO(orderDTO.getItemDTO()));
        this.setCalculatedTaxes(new ArrayList<CalculatedTax>());
    }


   /* private List<Item> createListItemDeItemDTO(List<ItemDTO> itemDTOList) {
        List<Item> listItem = new ArrayList<>();
        if(itemDTOList != null) {
            //for (int j = 0; j < itemDTOList.size(); j++) {
            for(ItemDTO i : itemDTOList) {
                Item item;

                if(i.getIdAsset() != null) {
                    item = ItemFactory.getInstance().crear(i.getIdAsset());
                    item.updateFromDto(i);
                } else {
                    item = new Item(itemDTOList.get(j));
                }
                listItem.add(item);
            }
        }
        return listItem;
    }*/
    private List<Item> createListItemDeItemDTO(List<ItemDTO> listItemDTO) throws EmptyElementException
    {
        List<Item> listItem = new ArrayList<>();
        if(listItemDTO == null)
        {
            throw new EmptyElementException("La lista de los itemDTO esta vacia");
        }
        else
        {
            for(ItemDTO list : listItemDTO)
            {
                Item item = new Item(list);
                listItem.add(item);
            }
        }
        return listItem;
    }
}
