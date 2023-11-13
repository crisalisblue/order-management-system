package crisalis.blue.models;

import crisalis.blue.models.dto.CustomerDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue(value = "BUS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Business extends Customer {

    public Business(CustomerDTO dto){
        super(dto);
        setBusinessName(dto.getBusinessName());
        setActivityStartDate(dto.getActivityStartDate());
        setCuit(dto.getCuit());
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "business_person",
            joinColumns = {
                    @JoinColumn(name = "customer_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "customer_id_person", referencedColumnName = "id")
            }
    )
    private List<Person> persons = new ArrayList<Person>();

    private String businessName;
    private Date activityStartDate;
    private String cuit;

    @Override
    protected CustomerDTO completeSpecificAttrib(CustomerDTO dto) {
        dto.setType("BUS");
        dto.setBusinessName(this.businessName);
        dto.setActivityStartDate(this.activityStartDate);
        dto.setCuit(this.cuit);
        return dto;
    }
}
