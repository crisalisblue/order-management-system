package crisalis.blue.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import crisalis.blue.models.dto.CustomerDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;


    protected Customer (CustomerDTO dto){
        setId(dto.getId());
        setName(dto.getName());
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

}