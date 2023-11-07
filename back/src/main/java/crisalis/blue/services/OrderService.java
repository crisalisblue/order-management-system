package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.*;
import crisalis.blue.models.dto.OrderDTO;
import crisalis.blue.repositories.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final CalculatedTaxRepository calculatedTaxRepository;
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
                        ItemRepository itemRepository,CalculatedTaxRepository calculatedTaxRepository)
    {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.calculatedTaxRepository = calculatedTaxRepository;
    }
    public OrderDTO create(OrderDTO order)
    {
        Optional<Customer> optionalCustomer = null;
        Customer customer = null;
        if(chekcEmptyOrder(order))
        {
            if(order.getIdCustomer() != null)
            {
             if( customerRepository.findById(order.getIdCustomer()).isPresent())
             {
                 optionalCustomer = customerRepository.findById(order.getIdCustomer());
                 customer = optionalCustomer.get();
             }
            }

            return orderRepository.save(new Order(null,order.getTotalDiscount(),order.getDateOrder()
                    ,order.getActive(), order.getTotalPrice(),order.getSubTotal()
                    ,customer,buscarAsset(order.getIdItem()),
                    buscarTaxes(order.getIdCalculatedTaxes()))).toOrderDTO();
     }
     throw new RuntimeException();

    }
    private List<Item> buscarAsset(List<Long> idItems)
    {
       if(idItems != null)
       {
           if(!idItems.isEmpty())
           {
               List<Item> listAsset = new ArrayList<Item>();
               Long actual = null;
               for(int j=0;j<idItems.size();j++)
               {
                   actual = idItems.get(j);
                   Optional<Item> optionalAsset = itemRepository.findById(actual);
                   if(optionalAsset.isPresent())
                       listAsset.add(optionalAsset.get());
               }
               return listAsset;
           }
       }
       return null;
    }
    private List<CalculatedTax> buscarTaxes(List<Long>idTaxes)
    {
        if(idTaxes != null)
        {
            if(!idTaxes.isEmpty())
            {
                List<CalculatedTax> listTaxes = new ArrayList<CalculatedTax>();
                Long actual = null;
                for(int j=0; j<idTaxes.size();j++)
                {
                    actual = idTaxes.get(j);
                    Optional<CalculatedTax> optionalTax = calculatedTaxRepository.findById(actual);
                    if(optionalTax.isPresent())
                        listTaxes.add(optionalTax.get());
                }
                return listTaxes;
            }
        }
        return null;
    }
    public List<OrderDTO> read()
    {
        return orderRepository.findAll().stream().map(Order::toOrderDTO).collect(Collectors.toList());
    }
    public OrderDTO update(  OrderDTO order)
    {
        Optional<Order> aux = orderRepository.findById(order.getIdOrder());
        if(aux.isPresent()) {
            if (order.getDateOrder() != null)
                aux.get().setDatesOrder(order.getDateOrder());
            if (order.getTotalPrice()!=null && order.getTotalPrice().intValue() != 0)
                aux.get().setTotalPrice(order.getTotalPrice());
            if(order.getSubTotal()!=null && order.getSubTotal().intValue() != 0)
                aux.get().setSubTotal(order.getSubTotal());
            if(order.getTotalDiscount()!=null && order.getTotalDiscount().intValue() != 0)
                aux.get().setTotalDiscount(order.getTotalDiscount());
            if (order.getIdCustomer() != null) {
                if(customerRepository.existsById(order.getIdCustomer()))
                    aux.get().setCustomer(customerRepository.findById(order.getIdCustomer()).get());
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
    private boolean chekcEmptyOrder(OrderDTO order)
    {
        if(order.getDateOrder() != null)
        {
            if(order.getTotalPrice().intValue() != 0)
            {
                if(order.getSubTotal().intValue() != 0)
                {
                    if(order.getTotalDiscount().intValue() != 0)
                    {
                        return true;
                    }
                }

            }
        }
        throw new EmptyElementException("Los campos estan vacios ");
    }

}
