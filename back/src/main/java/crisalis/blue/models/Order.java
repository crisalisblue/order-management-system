package crisalis.blue.models;

import crisalis.blue.models.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


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
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE,optional = true)
    @JoinColumn(name="customer_id",referencedColumnName = "id")
    private Customer customer;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "order_items")
    private List<Item> items;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private List<CalculatedTax> calculatedTaxes;



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
            orderDTO.setCustomerId(this.getCustomer().getId());
        if(this.getSubTotal().intValue() != 0)
            orderDTO.setSubTotal(this.getSubTotal());
        orderDTO.setActive(this.active);
        if(this.getCalculatedTaxes() != null )
        {
            if(!this.getCalculatedTaxes().isEmpty())
            {
                for(int j = 0; j<this.getCalculatedTaxes().size(); j++)
                {
                    orderDTO.getIdTaxes().add(this.getCalculatedTaxes().get(j).getId());
                }
            }
        }
        if(this.getItems() != null)
        {
            if(!this.getItems().isEmpty())
            {
                for(int j=0; j<this.getItems().size(); j++)
                {
                    orderDTO.getIdItem().add(this.getItems().get(j).getId());
                }
            }
        }
        return orderDTO;
    }


}
