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
public class OrderRefreshDTO {

    private Long idOrder;
    @Temporal(TemporalType.DATE)
    private Date dateOrder;
    private BigDecimal totalDiscount;
    private BigDecimal totalPrice;
    private BigDecimal subTotal;
    private Boolean active;
    private Long customerID;
    private String customerName;
    private String action;
    private List<ItemRefreshDTO> itemDTO;
    private List<CalculatedTaxDTO> calculatedTaxDTOS;

    public void OrderDTO() {
        itemDTO = new ArrayList<ItemRefreshDTO>();
    }

    public Order toOrder() {
        Order order = new Order();
        order.setId(this.getIdOrder());
        order.setDatesOrder(this.getDateOrder());
        order.setSubTotal(this.getSubTotal());
        order.setActive(this.getActive());
        order.setTotalDiscount(this.getTotalDiscount());
        order.setTotalPrice(this.getTotalPrice());
        return order;
    }
}
