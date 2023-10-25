package crisalis.blue.controllers;

import crisalis.blue.models.Customer;
import crisalis.blue.models.dto.CustomerDTO;
import crisalis.blue.services.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO createCustomer(@RequestBody Customer customer) throws Exception {
        return this.customerService.createCustomer(customer);
    }

    @PutMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO updateCustomer(@RequestBody Customer customer) throws Exception {
        return clientCustomer.updateCustomer(customer);
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomers() {
        return this.clientCustomer.getListOfAllCustomerInDB();
    }

    /*
    //Si no funciona el mapping utilizando el RequestParam, usar este y fijarse
    // la diferencia

    @DeleteMapping(value = "/{id}")
    public ClientDTO deleteClient(@PathVariable(value = "id") int id) {
        return clientService.deleteClient(id);
    }
    */
    @DeleteMapping(value = "")
    public CustomerDTO deleteCustomer(@RequestParam int id ) {
        return CustomerService.deleteClient(id);
    }


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    //Si no funciona cambiar int por Integer y fijarse.
    public CustomerDTO getCustomerById(@RequestParam int id) {
        return this.CustomerService.getCustomerById(id);
    }
}
