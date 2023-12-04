package crisalis.blue.models;

import crisalis.blue.models.dto.ReturnSubscriptionDTO;
import crisalis.blue.models.dto.SubscriptionDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @SequenceGenerator(name = "subscription_sequence", sequenceName = "subscription_sequence", allocationSize = 1, initialValue = 1

    )
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "subscription_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_asset", referencedColumnName = "id")
    private Asset asset;

    public SubscriptionDTO toDTO() {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setId(this.id);
        dto.setStatus(this.status);
        dto.setCustomer(this.customer.getId());
        dto.setAsset(this.asset.getId());
        return dto;
    }

    public ReturnSubscriptionDTO toReturnDTO() {
        ReturnSubscriptionDTO dto = new ReturnSubscriptionDTO();
        dto.setId(this.id);
        // Determinando el boolean de status para devolver un string

        if (this.status) {
            dto.setStatus("Activo");
        } else {
            dto.setStatus("Inactivo");
        }
        dto.setCustomer(this.customer.getName());
        dto.setAsset(this.asset.getName());
        return dto;
    }
}
