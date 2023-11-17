package crisalis.blue.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("customer")
    private Long customer;

    @JsonProperty("service")
    private Long service;

    @JsonProperty("status")
    private Boolean status;
}
