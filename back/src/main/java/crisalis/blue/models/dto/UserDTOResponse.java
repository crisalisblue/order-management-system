package crisalis.blue.models.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTOResponse {
    private String username;

    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private Integer id;
}
