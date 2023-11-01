package crisalis.blue.models.dto;


import crisalis.blue.models.Customer;
import crisalis.blue.models.ExchangeGood;
import crisalis.blue.models.ExchangeGood_Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {


    private Date DateOrder;
    private double totalDescount;
    private double totalAmount;
    private Customer customer;
}
