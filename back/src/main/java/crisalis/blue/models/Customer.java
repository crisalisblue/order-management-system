package crisalis.blue.models;

import crisalis.blue.models.dto.CustomerDTO;
import crisalis.blue.models.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1,
            initialValue = 10
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Integer idCustomer;

    @Column(
            name = "name",
            nullable = false)
    private String name;

    @Column(
            name = "lastName",
            nullable = false)
    private String lastName;

    @Column(
            name = "dni",
            nullable = false)
    private String dni;

    //Cuit en los modelos lo tenemos como double... deberia ser String ?
    @Column(name = "cuit",
            nullable = true)
    private String cuit;

    @Column(
            name = "activityStartDate",
            nullable = true)
    @Temporal(TemporalType.DATE)
    private Date activityStartDate;

    //razon social
    @Column(name = "businessName",
            nullable = true)
    private String businessName;

    @Column(name = "type",
            nullable = false)
    private String type;

    public Customer(Customer customer) {
        this.idCustomer = customer.getIdCustomer();
        this.name = customer.getName();
        this.lastName = customer.getLastName();
        this.dni = customer.getDni();
        this.cuit = customer.getCuit();
        this.activityStartDate = customer.getActivityStartDate();
        this.businessName = customer.getBusinessName();
        this.type = customer.getType();
    }

    public CustomerDTO toDTO(){
        return
                CustomerDTO
                        .builder()
                        .id(this.idCustomer)
                        .name(this.name)
                        .lastName(this.lastName)
                        .dni(this.dni)
                        .cuit(this.cuit)
                        .activityStartDate(this.activityStartDate)
                        .businessName(this.businessName)
                        .type(this.type)
                        .build();
    }
    /*
    return
                CustomerDTO
                        .builder()
                        .id(this.idCustomer)
                        .name(this.name)
                        .lastName(this.lastName)
                        .dni(this.dni)
                        .cuit(this.cuit)
                        .activityStartDate(this.activityStartDate)
                        .businessName(this.businessName)
                        .type(this.type)
                        .build();
    */


    //Relaciones con Entidades que aun no existen.

    /*
     //1 cliente con solo 1 persona
    @OneToOne
    @JoinColumn(name = "idPerson")
    private Person person
    */

    /*
    1 idcliente a N suscripciones, 1 suscripcion a 1 cliente
    @OneToMany(mappedBy = "idCustomer")
    private List<Suscription> suscriptions;
    */

    /*
    1 id cliente a N Pedidos, 1 Pedido a 1 Cliente
    @OneToMany(mappedBy = "idClient")
    private List<Order> orders;
    */





//    @ManyToMany
//    private List<Product> productList = new ArrayList<>();





}
