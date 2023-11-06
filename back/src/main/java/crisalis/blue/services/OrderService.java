package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.Customer;
import crisalis.blue.models.Order;
import crisalis.blue.models.dto.OrderDTO;
import crisalis.blue.repositories.CustomerRepository;
import crisalis.blue.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository)
    {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }
    public OrderDTO create(Order order)
    {
        Optional<Customer> optionalCustomer = null;
        if(chekcEmptyOrder(order))
        {
            if(order.getCustomer().getId() != null)
            {
             if( customerRepository.findById(order.getCustomer().getId()).isPresent())
             {
                 optionalCustomer = customerRepository.findById(order.getCustomer().getId());
                 order.setCustomer(optionalCustomer.get());
             }
            }
            else
                order.setCustomer(null);
            return orderRepository.save(order).toOrderDTO();
     }
     throw new RuntimeException();

    }
    public List<OrderDTO> read()
    {
        return orderRepository.findAll().stream().map(Order::toOrderDTO).collect(Collectors.toList());
    }
    public OrderDTO update(  Order order)
    {
        Optional<Order> aux = orderRepository.findById(order.getId_order());
        if(aux.isPresent()) {
            if (order.getDatesOrder() != null)
                aux.get().setDatesOrder(order.getDatesOrder());
            if (order.getTotalAmount().intValue() != 0)
                aux.get().setTotalAmount(order.getTotalAmount());
            if(order.getTotalDescount().intValue() != 0)
                aux.get().setTotalDescount(order.getTotalDescount());
            if (order.getCustomer().getId() != null) {
                aux.get().setCustomer(customerRepository.findById(order.getCustomer().getId()).get());
            }else
                aux.get().setCustomer(null);
            return orderRepository.save(aux.get()).toOrderDTO();
        }
        throw new EmptyElementException("No se encontro el registro con ese id ");
    }



    public void delete(Long id )
    {
        Optional<Order> aux = orderRepository.findById(id);
        if(aux.isPresent())
        {
            orderRepository.deleteById(id);
        }
        else
            throw new EmptyElementException("No se encontro el registro con ese id ");

    }
    private boolean chekcEmptyOrder(Order order)
    {
        if(order.getDatesOrder() != null)
        {
            if(order.getTotalAmount().intValue() != 0.0)
            {
                        return true;

            }
        }
        throw new EmptyElementException("Los campos estan vacios ");
    }

}
