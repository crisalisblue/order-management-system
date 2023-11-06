package crisalis.blue.models.dto;


import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
public class OrderDTO {

    @Temporal(TemporalType.DATE)
    private Date DateOrder;
    private BigDecimal totalDescount;
    private BigDecimal totalAmount;
    private Long customer_id;

    public OrderDTO(Date dateOrder, BigDecimal totalDescount, BigDecimal totalAmount,Long customer_id) {
        DateOrder = dateOrder;
        this.totalDescount = totalDescount;
        this.totalAmount = totalAmount;
        this.customer_id = customer_id;
    }
}
