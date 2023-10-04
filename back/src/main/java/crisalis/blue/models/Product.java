package crisalis.blue.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1,
            initialValue = 0
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;

    private String name;
    private BigDecimal price;
    
    @ManyToMany()
    @JoinTable(
        name = "Product_Customer_relationship",
        joinColumns = {
            @JoinColumn(name = "fk_Product")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "fk_Customer")
        }
    )
    private List<Customer> customerList = new ArrayList<>();

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private Set<OrderDetail> orderDetails = new HashSet<>(); // Set no permite duplicados en la coleccion



}
