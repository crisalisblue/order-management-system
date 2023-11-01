package crisalis.blue.models;

import crisalis.blue.models.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;


@Entity
@Table(name="Order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "DateOrder")
    private Date DateOrder;
    @Column(name = "activeOrder")
    private boolean active;
    @Column(name = "totalAmount")
    private double totalAmount;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="customer_id",referencedColumnName = "id")
    private Customer customer;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "exchangeGood_id",referencedColumnName = "id")
    private List<ExchangeGood> exchangeGood;

    public OrderDTO toOrderDTO()
    {
        return new OrderDTO(this.getDateOrder(), this.active,this.getTotalAmount(),this.getCustomer(),this.getExchangeGood());
    }


}
