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
    private final TaxRepository taxRepository;
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
                        ItemRepository itemRepository,TaxRepository taxRepository)
    {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.taxRepository = taxRepository;
    }
    public OrderDTO create(OrderDTO order)
    {
        Optional<Customer> optionalCustomer = null;
        Customer customer = null;
        if(chekcEmptyOrder(order))
        {
            if(order.getCustomerId() != null)
            {
             if( customerRepository.findById(order.getCustomerId()).isPresent())
             {
                 optionalCustomer = customerRepository.findById(order.getCustomerId());
                 customer = optionalCustomer.get();
             }
            }

            return orderRepository.save(new Order(null,order.getTotalDiscount(),order.getDateOrder()
                    ,order.getActive(),order.getSubTotal(), order.getTotalPrice()
                    ,customer,buscarAsset(order.getIdItem())/*,
                    buscarTaxes(order.getIdTaxes())*/)).toOrderDTO();
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
    private List<Tax> buscarTaxes(List<Long>idTaxes)
    {
        if(idTaxes != null)
        {
            if(!idTaxes.isEmpty())
            {
                List<Tax> listTaxes = new ArrayList<Tax>();
                Long actual = null;
                for(int j=0; j<idTaxes.size();j++)
                {
                    actual = idTaxes.get(j);
                    Optional<Tax> optionalTax = taxRepository.findById(actual);
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
            if (order.getTotalPrice().intValue() != 0)
                aux.get().setTotalPrice(order.getTotalPrice());
            if(order.getTotalDiscount().intValue() != 0)
                aux.get().setTotalDiscount(order.getTotalDiscount());
            if (order.getCustomerId() != null) {
                if(customerRepository.existsById(order.getCustomerId()))
                    aux.get().setCustomer(customerRepository.findById(order.getCustomerId()).get());
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
