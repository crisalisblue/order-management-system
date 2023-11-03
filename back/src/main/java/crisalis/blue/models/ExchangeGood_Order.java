package crisalis.blue.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ExchangeGood_Customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeGood_Order {
    // Id Bien de cambio-Pedido
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ExchangeGood_Customer")
    private Long id_ExchangeGood_Customer;

    // Id de pedido
    @JoinColumn(name = "id_Order",referencedColumnName = "id_Order")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private Order id_Order;

    // Id de de bien de cambio
    @JoinColumn(name="id_ExchangeGood")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private ExchangeGood id_ExchangeGood;

    // monto Item
    @Column(name = "exchangeGood_amount")
    private double exchangeGood_amount;

    // monto de descuento
    @Column(name = "discount_amount")
    private double discount_amount;

    // Descripción de items
    @Column(name= "description_exchangeGood")
    private String description_exchangeGood;
    // Cantidad
    @Column(name = "amount" )
    private int amount;
    // Impuestos Aplicados
    @Column(name = "taxes applied")
    private double taxes_applied;


}
