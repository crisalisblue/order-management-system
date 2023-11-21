package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.exceptions.custom.IntegrityViolationException;
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
                Person customerPerson = new Person(customer);
                this.customerRepository.save(customerPerson);
                return customerPerson.toDTO();
            } else {
                //Caso Business
                //Primero creo la persona que viene asociada a la empresa
                Person businessPerson = new Person(customer);
                this.customerRepository.save(businessPerson);
                //una vez guardado en la base de datos, ya se puede acceder al id automaticamente en el objeto previamente instanciado
                //System.out.println(businessPerson.getId());

                //Creo una lista nueva en la cual se va a asigna la persona que viene junto a la empresa
                Business customerBusiness = new Business(customer);
                List<Person> asociatedPerson = new ArrayList<>();
                asociatedPerson.add(businessPerson);

                customerBusiness.setPersons(asociatedPerson);

                this.customerRepository.save(customerBusiness);

                return customerBusiness.toDTO();
            }

        } catch (DataIntegrityViolationException | HibernateJdbcException e) {

            throw new NotCreatedException(e.getMessage());
        }

    }

    public CustomerDTO updateCustomer(CustomerDTO updatedCustomer) throws Exception {

        Optional<Customer> customerOptional = customerRepository.findById(updatedCustomer.getId());
        Customer returnCustomer = null;

        if (customerOptional.isPresent()) {
            //Vemos si el type es consistente con lo que necesitamos
            if (!updatedCustomer.getType().equals("PER") && !updatedCustomer.getType().equals("BUS")){
                throw new NotCreatedException("Error en el type recibido");
            }
            //Determinamos si lo que se esta updateando es una Persona o Empresa e instanciamos un objeto segun corresponda
            //Damos por hecho que siempre nos va a llegar el dto completo sin datos nulls, si no se actualiza el dato llegara el dato previo
            if (updatedCustomer.getType().equals("PER")){
                //Obtengo los datos de la persona persistidos
                Customer customerPerson = customerOptional.get();

                //Updateo los datos de la persona con los nuevos
                customerPerson.setName(updatedCustomer.getName());
                customerPerson.setAddress(updatedCustomer.getAddress());
                ((Person) customerPerson).setDni(updatedCustomer.getDni());
                ((Person) customerPerson).setLastName(updatedCustomer.getLastName());

                returnCustomer = customerPerson;
                customerRepository.save(customerPerson);

            } else if (updatedCustomer.getType().equals("BUS")){
                //Obtengo los datos de la empresa persistidos
                Customer customerBusiness = customerOptional.get();

                //Updateo los datos de la empresa con los nuevos
                customerBusiness.setAddress(updatedCustomer.getAddress());
                ((Business) customerBusiness).setBusinessName(updatedCustomer.getBusinessName());
                ((Business) customerBusiness).setCuit(updatedCustomer.getCuit());
                ((Business) customerBusiness).setActivityStartDate(updatedCustomer.getActivityStartDate());
                //Updateo los datos de la persona asociada a la empresa con los nuevos
                ((Business) customerBusiness).getPersons().get(0).setName(updatedCustomer.getName());
                ((Business) customerBusiness).getPersons().get(0).setLastName(updatedCustomer.getLastName());
                ((Business) customerBusiness).getPersons().get(0).setDni(updatedCustomer.getDni());

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

    public String deleteCustomer(Long id) {
        try {
            customerRepository.deleteById(id);
            return "Cliente " + id + " Borrado exitosamente";

        } catch (DataIntegrityViolationException e) {
            if (!customerRepository.existsById(id)) {
                throw new ResourceNotFoundException("No existe un usuario con id " + id + ".");
            }
            throw new IntegrityViolationException("Error al borrar");
            //throw new EmptyElementException(e.getMessage());
        }

    }

    public CustomerDTO getCustomerById(Long id) {

        return this.customerRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer not Found"))
                .toDTO();
    }

}