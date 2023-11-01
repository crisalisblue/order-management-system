package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.Order;
import crisalis.blue.models.dto.OrderDTO;
import crisalis.blue.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }
    public OrderDTO create(Order order)
    {
     if(chekcEmptyOrder(order))
     {
         return orderRepository.save(order).toOrderDTO();
     }
     throw new RuntimeException();

    }
    public List<OrderDTO> read()
    {
        return orderRepository.findAll().stream().map(Order::toOrderDTO).collect(Collectors.toList());
    }
    public OrderDTO update(Order order)
    {
        Optional<Order> aux = orderRepository.findById(order.getId_order());
        if(aux.isPresent())
        {
            if(order.getDatesOrder() != null)
                aux.get().setDatesOrder(order.getDatesOrder());
            if(order.getTotalAmount() != 0.0)
                aux.get().setTotalAmount(order.getTotalAmount());
            if(order.getCustomer() != null )
                aux.get().setCustomer(order.getCustomer());
            orderRepository.save(aux.get());
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
            if(order.getTotalAmount() != 0.0)
            {
                if(order.getCustomer() != null)
                {

                        return true;

                }
            }
        }
        throw new EmptyElementException("Los campos estan vacios ");
    }

}
