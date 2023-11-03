package crisalis.blue.models;

import crisalis.blue.models.dto.CustomerDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(
            name = "name")
    private String name;

    @Column(
            name = "lastName")
    private String lastName;

    @Column(
            name = "dni",unique = true)
    private String dni;

    @Column(name = "cuit")
    private String cuit;

    @Column(
            name = "activityStartDate")
    @Temporal(TemporalType.DATE)
    private Date activityStartDate;

    //razon social
    @Column(name = "businessName")
    private String businessName;

    @Column(name = "type")
    private String type;

    public Customer(Customer customer) {
        this.id = customer.getId();
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
                        .id(this.id)
                        .name(this.name)
                        .lastName(this.lastName)
                        .dni(this.dni)
                        .cuit(this.cuit)
                        .activityStartDate(this.activityStartDate)
                        .businessName(this.businessName)
                        .type(this.type)
                        .build();
    }


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
