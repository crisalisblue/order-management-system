package crisalis.blue.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import crisalis.blue.models.dto.CustomerDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name ="type",
        discriminatorType = DiscriminatorType.STRING
)
@Table(name = "customer")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public abstract class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1,
            initialValue = 1

    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "customer_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(insertable = false,
            updatable = false)
    private String type;


    /*public Customer(CustomerDTO customer) {
        this.id = customer.getId();
        this.address = customer.getAddress();
        this.type = customer.getType();
    }*/

    protected Customer (CustomerDTO dto){
        setId(dto.getId());
        setAddress(dto.getAddress());
    }


    protected abstract CustomerDTO completeSpecificAttrib(CustomerDTO dto);

    public CustomerDTO toDTO(){
        CustomerDTO dto = new CustomerDTO();
        dto.setId(id);
        dto.setAddress(address);
        dto = completeSpecificAttrib(dto);
        return dto;
    }


    // Relaciones con Entidades que aun no existen.

    /*
     * 1 idcliente a N suscripciones, 1 suscripcion a 1 cliente
     * 
     * @OneToMany(mappedBy = "idCustomer")
     * private List<Suscription> suscriptions;
     */

    /*
     * 1 id cliente a N Pedidos, 1 Pedido a 1 Cliente
     * 
     * @OneToMany(mappedBy = "idClient")
     * private List<Order> orders;
     */

    // @ManyToMany
    // private List<Product> productList = new ArrayList<>();

}