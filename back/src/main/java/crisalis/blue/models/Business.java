package crisalis.blue.models;

import crisalis.blue.models.dto.CustomerDTO;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;
@Entity
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
        this.per = per;
    }

    private String businessName;
    private Date activityStartDate;
    private String cuit;
    private Person per;

    @Override
    protected CustomerDTO completeSpecificAttrib(CustomerDTO dto) {
        dto.setType("BUS");
        dto.setBusinessName(this.businessName);
        dto.setActivityStartDate(this.activityStartDate);
        dto.setCuit(this.cuit);
        return dto;
    }
}
