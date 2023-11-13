package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.exceptions.custom.NotCreatedException;
import crisalis.blue.exceptions.custom.ResourceNotFoundException;
import crisalis.blue.models.Business;
import crisalis.blue.models.Customer;
import crisalis.blue.models.Person;
import crisalis.blue.models.User;
import crisalis.blue.models.dto.CustomerDTO;
import crisalis.blue.models.dto.UserDTO;
import crisalis.blue.repositories.CustomerRepository;
import crisalis.blue.repositories.UserRepository;
import crisalis.blue.validators.Encrypt;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
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

    public CustomerDTO createCustomer(CustomerDTO customer) throws Exception {
        try {
            if (customer.getType().equals("PER")){
                Customer customerPerson = new Person(customer);
                this.customerRepository.save(customerPerson);
                return customerPerson.toDTO();
            } else {
                //Caso Business
                Customer customerBusiness = new Business(customer);
                this.customerRepository.save(customerBusiness);
                return customerBusiness.toDTO();
            }

        } catch (DataIntegrityViolationException | HibernateJdbcException e) {
            throw new NotCreatedException(e.getMessage());
        }

    }

    public CustomerDTO updateCustomer(CustomerDTO updatedCustomer) throws Exception {

        Optional<Customer> customerOptional = customerRepository.findById(updatedCustomer.getId().intValue());
        Customer returnCustomer = null;

        if (customerOptional.isPresent()) {
            //Vemos si el type es consistente con lo que necesitamos
            if (!updatedCustomer.getType().equals("PER") && !updatedCustomer.getType().equals("PER")){
                throw new NotCreatedException("Error en el type recibido");
            }
            //Determinamos si lo que se esta updateando es una Persona o Empresa e instanciamos un objeto segun corresponda
            if (updatedCustomer.getType().equals("PER")){
                Person customerPerson = new Person(updatedCustomer);
                returnCustomer = customerPerson;
                customerRepository.save(customerPerson);

            } else if (updatedCustomer.getType().equals("BUS")){
                Business customerBusiness = new Business(updatedCustomer);
                returnCustomer = customerBusiness;
                customerRepository.save(customerBusiness);
            }
        }
        return returnCustomer.toDTO();
    }

    public List<CustomerDTO> getListOfAllCustomerInDB() {

        return this.customerRepository
                .findAll()
                .stream()
                .map(Customer::toDTO)
                .collect(Collectors.toList());
    }

    public String deleteCustomer(int id) {

        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe un usuario con id " + id + ".");
        }
        customerRepository.deleteById(id);
        return "Cliente " + id + " Borrado exitosamente";
    }

    public CustomerDTO getCustomerById(int id) {

        return this.customerRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer not Found"))
                .toDTO();
    }

}