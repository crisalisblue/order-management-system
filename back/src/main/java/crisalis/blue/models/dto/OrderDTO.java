package crisalis.blue.models.dto;


import crisalis.blue.models.Customer;
import crisalis.blue.models.ExchangeGood;
import crisalis.blue.models.ExchangeGood_Order;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @Temporal(TemporalType.DATE)
    private Date DateOrder;
    private double totalDescount;
    private double totalAmount;
    private Long customer;

    public OrderDTO(Date dateOrder, double totalDescount, double totalAmount) {
        DateOrder = dateOrder;
        this.totalDescount = totalDescount;
        this.totalAmount = totalAmount;
    }
}
