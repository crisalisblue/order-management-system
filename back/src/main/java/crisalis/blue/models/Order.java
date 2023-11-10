package crisalis.blue.models;

import crisalis.blue.models.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name="Orderby")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_order")
    private Long id;
    @Column(name = "totalDiscount")
    private BigDecimal totalDiscount;
    @Temporal(TemporalType.DATE)
    @Column(name = "datesOrder")
    private Date datesOrder;
    @Column(name="active")
    private boolean active;
    @Column(name = "totalPrice")
    private BigDecimal totalPrice;
    @Column(name= "subTotal")
    private BigDecimal subTotal;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name="customer_id",referencedColumnName = "id")
    private Customer customer;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Item> items;





    public OrderDTO toOrderDTO()
    {
        OrderDTO orderDTO = new OrderDTO();
        if(this.getId() != 0)
            orderDTO.setIdOrder(this.getId());
        if(this.getDatesOrder() != null)
            orderDTO.setDateOrder(this.getDatesOrder());
        if(this.getTotalDiscount().intValue() != 0)
            orderDTO.setTotalDiscount(this.getTotalDiscount());
        if(this.getTotalPrice().intValue() != 0)
            orderDTO.setTotalPrice(this.getTotalPrice());
        if(this.getCustomer() != null)
            orderDTO.setCustomerDTO(this.getCustomer().toDTO());
        if(this.getSubTotal().intValue() != 0)
            orderDTO.setSubTotal(this.getSubTotal());
        orderDTO.setActive(this.active);

        if(this.getItems() != null && !this.getItems().isEmpty())
        {
            orderDTO.setItemDTO(this.getItems().stream().map(Item::toItemDTO).collect(Collectors.toList()));
        }
        return orderDTO;
    }


}
