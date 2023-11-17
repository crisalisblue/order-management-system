package crisalis.blue.models;

import crisalis.blue.models.dto.CustomerDTO;
import crisalis.blue.models.dto.SubscriptionDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "subscription")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    public Subscription(SubscriptionDTO dto){
        setStatus(this.status);
        setCustomer(this.customer);
        setService(this.service);
    }

    @Id
    @SequenceGenerator(
            name = "subscription_sequence",
            sequenceName = "subscription_sequence",
            allocationSize = 1,
            initialValue = 1

    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "customer_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service")
    private Service service;

    public SubscriptionDTO toDTO(){
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setId(this.id);
        dto.setStatus(this.status);
        dto.setCustomer(this.customer.getId());
        dto.setService(this.customer.getId());
        return dto;
    }
}
