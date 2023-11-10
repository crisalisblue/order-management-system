package crisalis.blue.models.dto;


import crisalis.blue.models.Order;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {


    private Long idOrder;
    @Temporal(TemporalType.DATE)
    private Date dateOrder;
    private BigDecimal totalDiscount;
    private BigDecimal totalPrice;
    private BigDecimal subTotal;
    private Boolean active;
    private CustomerDTO customerDTO;
    private List<ItemDTO> itemDTO;
    public OrderDTO()
    {
        itemDTO = new ArrayList<ItemDTO>();
       // idTaxes = new ArrayList<Long>();
    }
    public Order toOrder()
    {
        Order order = new Order();
        order.setId(this.getIdOrder());
        order.setDatesOrder(this.getDateOrder());
        order.setCustomer(this.getCustomerDTO().toCustomer());
        order.setSubTotal(this.getSubTotal());
        order.setActive(this.getActive());
        order.setTotalDiscount(this.getTotalDiscount());
        order.setTotalPrice(this.getTotalPrice());
        if(this.customerDTO != null)
            order.setCustomer(this.getCustomerDTO().toCustomer());
        return order;
    }
}
