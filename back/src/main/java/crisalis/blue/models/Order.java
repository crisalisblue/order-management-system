package crisalis.blue.models;

import crisalis.blue.models.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.aspectj.weaver.ast.Or;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name="Orderby")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_order")
    private Long id_order;
    @Temporal(TemporalType.DATE)
    @Column(name = "datesOrder")
    private Date datesOrder;
    @Column(name = "totalDescount")
    private BigDecimal totalDescount;
    @Column(name = "totalAmount")
    private BigDecimal totalAmount;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE,optional = true)
    @JoinColumn(name="customer_id",referencedColumnName = "id")
    private Customer customer;

    public OrderDTO toOrderDTO()
    {
        OrderDTO orderDTO = new OrderDTO();
        if(this.getDatesOrder() != null)
            orderDTO.setDateOrder(this.getDatesOrder());
        if(this.getTotalDescount().intValue() != 0)
            orderDTO.setTotalDescount(this.getTotalDescount());
        if(this.getTotalAmount().intValue() != 0)
            orderDTO.setTotalAmount(this.getTotalAmount());
        if(this.getCustomer() != null)
            orderDTO.setCustomer_id(this.getCustomer().getId());
        return orderDTO;
    }


}
