package crisalis.blue.controllers;

import crisalis.blue.models.User;
import crisalis.blue.models.dto.JwtDTO;
import crisalis.blue.models.dto.LoginDTO;
import crisalis.blue.models.dto.UserDTO;
import crisalis.blue.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
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


    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO createdUser(@RequestBody User user){
        return this.userService.createdUser(user);
    }

    @PutMapping(value="update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO updateUser( @RequestBody User user){
        return  userService.updateUser(user);

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User saveUser(@RequestBody UserDTO userDTO) throws Exception{
            return this.userService.saveUser(userDTO);
    }

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtDTO loginUser(HttpServletResponse response, @RequestBody LoginDTO loginDTO) throws Exception {
        return this.userService.loginUserWithCredentials(loginDTO.getUsername(), loginDTO.getPassword());

    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        return this.userService.getListOfAllUsersInDB();

    }

    @DeleteMapping(value="/{id}")
    public UserDTO deleteUser(@PathVariable(value="id") int id){
        return userService.deleteUser(id);

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserById(@RequestParam Integer id){
        return this.userService.getUserById(id);

    }


}
