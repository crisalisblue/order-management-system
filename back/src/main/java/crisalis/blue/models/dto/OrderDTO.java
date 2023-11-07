package crisalis.blue.models.dto;


import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {

    @Temporal(TemporalType.DATE)
    private Long idOrder;
    private Date dateOrder;
    private BigDecimal totalDiscount;
    private BigDecimal totalPrice;
    private BigDecimal subTotal;
    private Boolean active;
    private Long customerId;
    private List<Long> idItem;
    //private List<Long> idTaxes;
    public OrderDTO()
    {
        idItem = new ArrayList<Long>();
       // idTaxes = new ArrayList<Long>();
    }
}
