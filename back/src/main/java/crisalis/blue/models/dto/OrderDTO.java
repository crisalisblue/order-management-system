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


    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateOrder;
    private BigDecimal totalDiscount;
    private BigDecimal totalPrice;
    private BigDecimal subTotal;
    private Boolean active;
    private Long customerID;
    private String customerName;
    private List<ItemDTO> itemDTO;
    private List<CalculatedTaxDTO> calculatedTaxDTOS;
    private String action;
    public OrderDTO()
    {
        itemDTO = new ArrayList<ItemDTO>();
    }

}
