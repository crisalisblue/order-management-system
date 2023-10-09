package crisalis.blue.controllers;

import crisalis.blue.models.User;
import crisalis.blue.models.dto.UserDTO;
import crisalis.blue.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User saveUser(@RequestBody UserDTO userDTO){
        return this.userService.saveUser(userDTO);
    }

    // No se como vamos a implementar el login por ahora lo dejo comentado

    /*@GetMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO loginUser(@RequestParam String username, @RequestParam String password){
        return this.userService.loginUserWithCredentials(username, password);
    }*/
    @PostMapping(value="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody UserDTO userDto){

    }
    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        return this.userService.getListOfAllUsersInDB();

    }
    //@PostMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
    //public void createUser()


}
