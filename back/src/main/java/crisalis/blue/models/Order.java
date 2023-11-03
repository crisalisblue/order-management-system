package crisalis.blue.models;

import crisalis.blue.models.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.Data;

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
    private double totalDescount;
    @Column(name = "totalAmount")
    private double totalAmount;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE,optional = true)
    @JoinColumn(name="customer_id",referencedColumnName = "id")
    private Customer customer;

    public OrderDTO toOrderDTO()
    {

        return new OrderDTO(this.getDatesOrder(), this.getTotalDescount(),this.getTotalAmount());
    }


}
