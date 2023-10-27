package crisalis.blue.services;

import crisalis.blue.exceptions.custom.NotCreatedException;
import crisalis.blue.models.Customer;
import crisalis.blue.models.dto.CustomerDTO;
import crisalis.blue.repositories.CustomerRepository;
import crisalis.blue.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public CustomerDTO updateCustomer(Customer updatedCustomer) throws Exception {

        Optional<Customer> customerOptional = customerRepository.findById(updatedCustomer.getId().intValue());

        if (customerOptional.isPresent()){
            //Guardamos en customer, los datos del cliente que esta en la base de datos.
            Customer customer = customerOptional.get();

            //a ese customer le asignamos los nuevos valores recibidos en updatedCustomer
            customer.setName(updatedCustomer.getName());
            customer.setLastName(updatedCustomer.getLastName());
            customer.setDni(updatedCustomer.getDni());
            customer.setCuit(updatedCustomer.getDni());
            customer.setActivityStartDate(updatedCustomer.getActivityStartDate());
            customer.setBusinessName(updatedCustomer.getBusinessName());
            customer.setType(updatedCustomer.getType());

            //Guardamos el cliente ya actualizado.
            customerRepository.save(customer);

            return customer.toDTO();
        }
        throw new NotCreatedException("Error updating Customer");
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
