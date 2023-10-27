package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.exceptions.custom.NotCreatedException;
import crisalis.blue.models.Customer;
import crisalis.blue.models.User;
import crisalis.blue.models.dto.CustomerDTO;
import crisalis.blue.repositories.CustomerRepository;
import crisalis.blue.repositories.UserRepository;
import crisalis.blue.validators.Encrypt;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    public CustomerDTO createCustomer(Customer customer) throws Exception{
        try {
            this.customerRepository.save(customer);
            return customer.toDTO();
        }catch (DataIntegrityViolationException | HibernateJdbcException e){
            throw new NotCreatedException(e.getMessage());
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

        return this
                .customerRepository
                .findAll()
                .stream()
                .map(Customer::toDTO)
                .collect(Collectors.toList());
    }

    public String deleteCustomer(int id) {
        if (customerRepository.existsById(id)){
            customerRepository.deleteById(id);
            return "Cliente " + id +" Borrado exitosamente";
        }
        throw new EmptyElementException("No existe un usuario con id " + id + ".");

    }

    public CustomerDTO getCustomerById(int id) {
        Customer newCustomer = null;


        return newCustomer.toDTO();
    }


}
