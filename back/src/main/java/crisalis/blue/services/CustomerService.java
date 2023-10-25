package crisalis.blue.services;

import crisalis.blue.exceptions.custom.NotCreatedException;
import crisalis.blue.models.Customer;
import crisalis.blue.models.dto.CustomerDTO;
import crisalis.blue.repositories.CustomerRepository;
import crisalis.blue.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    public CustomerDTO createCustomer(Customer customer) throws Exception{
        try {
            Customer newCustomer = new Customer(customer);
            this.customerRepository.save(newCustomer);

            return newCustomer.toDTO();
        }catch (Error e){
            throw new NotCreatedException("Error 400 bad request.");
        }

    }
/*
    public CustomerDTO updateCustomer(Customer customer) {
    }

    public List<CustomerDTO> getListOfAllCustomerInDB() {
    }

    public CustomerDTO deleteClient(int id) {
    }

    public CustomerDTO getCustomerById(int id) {

    }

 */
}
