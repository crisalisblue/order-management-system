package crisalis.blue.models;

import crisalis.blue.models.dto.UserDTO;
import crisalis.blue.models.dto.UserDTOResponse;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;


    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    public User(User user){
        this.name = user.getName();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }


    public UserDTO toDTO(){
        return
                UserDTO
                        .builder()
                        .name(this.name)
                        .password(this.password)
                        .username(this.username)
                        .id(this.id)
                        .build();
    }
    public UserDTOResponse toDTOResponse(){
        return
                UserDTOResponse
                        .builder()
                        .name(this.name)
                        .username(this.username)
                        .id(this.id)
                        .build();
    }

}
