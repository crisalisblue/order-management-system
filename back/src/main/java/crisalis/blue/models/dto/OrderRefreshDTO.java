package crisalis.blue.models.dto;

import crisalis.blue.models.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderRefreshDTO extends OrderDTO {
    private String action;
    private List<ItemRefreshDTO> itemDTO;

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
