package crisalis.blue.services;

import crisalis.blue.exceptions.custom.NotCreatedException;
import crisalis.blue.models.Customer;
import crisalis.blue.models.dto.CustomerDTO;
import crisalis.blue.repositories.CustomerRepository;
import crisalis.blue.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public CustomerDTO updateCustomer(Customer customer) {
        Customer newCustomer = new Customer(customer);

        return newCustomer.toDTO();
    }

    public List<CustomerDTO> getListOfAllCustomerInDB() {
        List<CustomerDTO> test = new ArrayList<>();

        return test;
    }

    public CustomerDTO deleteClient(int id) {
        Customer newCustomer = null;

        return newCustomer.toDTO();
    }

    public CustomerDTO getCustomerById(int id) {
        Customer newCustomer = null;


        return newCustomer.toDTO();
    }


}
