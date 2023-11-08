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

        if (customerOptional.isPresent()) {
            // Guardamos en customer, los datos del cliente que esta en la base de datos.
            Customer customer = customerOptional.get();

            //Determinamos si lo que se esta updateando es una Persona o Empresa e instanciamos un objeto segun corresponda
            if (customer.getType() == "PER"){
                Customer customerPerson = new Person(updatedCustomer);

                //Para poder acceder a los atributos de Persona, hay que hacer una referencia temporal.
                ((Person) customerPerson).setName(updatedCustomer.getName());
                ((Person) customerPerson).setLastName(updatedCustomer.getLastName());
                ((Person) customerPerson).setDni(updatedCustomer.getDni());
                /*
                ((Person) customerPerson).setName(((Person) updatedPerson).getName());
                ((Person) customerPerson).setLastName(((Person) updatedPerson).getLastName());
                ((Person) customerPerson).setDni(((Person) updatedPerson).getDni());
                */

                customerRepository.save(customerPerson);
                return ((Person) customerPerson).toDTO();

            } else if (customer.getType() == "BUS"){
                Customer customerBusiness = new Business(updatedCustomer);

                ((Business) customerBusiness).setBusinessName(updatedCustomer.getBusinessName());
                ((Business) customerBusiness).setActivityStartDate(updatedCustomer.getActivityStartDate());
                ((Business) customerBusiness).setCuit(updatedCustomer.getCuit());

                customerRepository.save(customerBusiness);
                return ((Business) customerBusiness).toDTO();
            }
        }
        throw new NotCreatedException("Error updating Customer");
    }

    /*public List<CustomerDTO> getListOfAllCustomerInDB() {

        return this.customerRepository
                .findAll()
                .stream()
                .map(Customer::toDTO)
                .collect(Collectors.toList());
    }*/

    public String deleteCustomer(int id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return "Cliente " + id + " Borrado exitosamente";
        }
        throw new EmptyElementException("No existe un usuario con id " + id + ".");

    }

    /*public CustomerDTO getCustomerById(int id) {

        return this.customerRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer not Found"))
                .toDTO();
    }*/

}