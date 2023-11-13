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


    // Relaciones

    //Relacion 1 a N con Suscriptions

    /*@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "idSuscription")
    private List<Suscription> suscriptions;*/

    //Relacion 1 a N con Order

     /*@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "idOrder")
     private List<Order> orders;*/

    //Relacion 1 a N con Empresa
    @OneToMany
    @JoinTable(
            name = "Business_Customer",
            joinColumns = {
                    @JoinColumn(name = "customer_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "business_id", referencedColumnName = "id")
            }
    )
    private List<Business> businesses = new ArrayList<Business>();

}