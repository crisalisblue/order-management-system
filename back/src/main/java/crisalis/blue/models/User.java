package crisalis.blue.models;

import crisalis.blue.models.dto.UserDTO;
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
    private Integer id;

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
                        .username(this.username)
                        .id(this.id)
                        .build();
    }

}
