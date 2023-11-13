package crisalis.blue.controllers;

import crisalis.blue.models.Business;
import crisalis.blue.models.Customer;
import crisalis.blue.models.Person;
import crisalis.blue.models.dto.CustomerDTO;
import crisalis.blue.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customer) throws Exception {
        return this.customerService.createCustomer(customer);
    }

    @PutMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE)

    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customer) throws Exception {
        return customerService.updateCustomer(customer);
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomers() {
        return this.customerService.getListOfAllCustomerInDB();
    }

    @DeleteMapping(value = "")
    public String deleteCustomer(@RequestParam int id) {
        return customerService.deleteCustomer(id);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO getCustomerById(@RequestParam int id) {
        return this.customerService.getCustomerById(id);
    }
}