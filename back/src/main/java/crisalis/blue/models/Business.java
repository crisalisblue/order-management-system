package crisalis.blue.models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Business extends Customer {

    public Business(Long id, String address, String businessName, Date activityStartDate, String cuit, Person per){
        super(id, address, "Business");
        this.businessName = businessName;
        this.activityStartDate = activityStartDate;
        this.cuit = cuit;
        this.per = per;
    }

    private String businessName;
    private Date activityStartDate;
    private String cuit;
    private Person per;
}
