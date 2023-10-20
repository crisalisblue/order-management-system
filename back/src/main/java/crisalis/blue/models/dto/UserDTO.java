package crisalis.blue.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @JsonProperty("username") // si el frontend trabaja en espaniol, lo puedo asociar a mi nombre en ingles
    private String username;

    @JsonProperty("name")
    private String name;
    @JsonProperty("name")
    private String password;
    @JsonProperty("id")
    private Integer id;
}
