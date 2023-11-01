package crisalis.blue.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ExchangeGood_Customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeGood_Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ExchangeGood_Customer")
    private Long id_ExchangeGood_Customer;
    @JoinColumn(name = "id_Customer")
    @ManyToOne
    private Customer id_Customer;
    @JoinColumn(name="id_ExchangeGood")
    @ManyToOne
    private ExchangeGood id_ExchangeGood;
    @Column(name = "exchangeGood_amount")
    private double exchangeGood_amount;
    @Column(name = "discount_amount")
    private double discount_amount;
    @Column(name= "description_exchangeGood")
    private String description_exchangeGood;
    @Column(name = "amount" )
    private int amount;
    @Column(name = "taxes applied")
    private double taxes_applied;


}
