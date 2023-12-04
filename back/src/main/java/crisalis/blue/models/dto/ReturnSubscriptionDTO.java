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
public class ReturnSubscriptionDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("customer")
    private String customer;

    @JsonProperty("asset")
    private String asset;

    @JsonProperty("status")
    private String status;
}
