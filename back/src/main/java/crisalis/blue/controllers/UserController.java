package crisalis.blue.controllers;

import crisalis.blue.models.User;
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

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User saveUser(@RequestBody UserDTO userDTO) throws Exception{
            return this.userService.saveUser(userDTO);
    }

    @GetMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String loginUser(HttpServletResponse response, @RequestParam String username, @RequestParam String password) throws Exception {
        response.addHeader("auth", this.userService.loginUserWithCredentials(username, password));
        return "Usuario autorizado";
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers(){
        return this.userService.getListOfAllUsersInDB();

    }


}
