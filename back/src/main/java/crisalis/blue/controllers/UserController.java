package crisalis.blue.controllers;

import crisalis.blue.models.User;
import crisalis.blue.models.dto.JwtDTO;
import crisalis.blue.models.dto.LoginDTO;
import crisalis.blue.models.dto.UserDTO;
import crisalis.blue.models.dto.UserDTOResponse;
import crisalis.blue.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO createUser(@RequestBody User user) throws Exception {
        return this.userService.createUser(user);
    }

    @PutMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO updateUser(@RequestBody User user) throws Exception {
        return userService.updateUser(user);
    }

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtDTO loginUser(HttpServletResponse response, @RequestBody LoginDTO loginDTO) throws Exception {
        return this.userService.loginUserWithCredentials(loginDTO.getUsername(), loginDTO.getPassword());

    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers() {
        return this.userService.getListOfAllUsersInDB();

    }

    @DeleteMapping(value = "/{id}")
    public UserDTO deleteUser(@PathVariable(value = "id") int id) {
        return userService.deleteUser(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getUserById(@PathVariable Integer id) {
        return this.userService.getUserByIdAndDecryptPassword(id);
    }

}