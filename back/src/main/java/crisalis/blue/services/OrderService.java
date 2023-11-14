package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.*;
import crisalis.blue.models.dto.ItemDTO;
import crisalis.blue.models.dto.OrderDTO;
import crisalis.blue.repositories.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final TaxRepository taxRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;
    private final CalculatedTaxRepository calculatedTaxRepository;
    private final AssetRepository assetRepository;
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
                        ItemRepository itemRepository,
                        CalculatedTaxRepository calculatedTaxRepository, AssetRepository assetRepository
                        ,TaxRepository taxRepository)
    {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.calculatedTaxRepository = calculatedTaxRepository;
        this.assetRepository = assetRepository;
        this.taxRepository = taxRepository;
    }
    public OrderDTO create(OrderDTO orderDTO)
    {
        if(orderDTO != null) {
            Order order = new Order();
            if(checkEmpty(orderDTO)) {
                order.setDatesOrder(orderDTO.getDateOrder());
                order.setActive(orderDTO.getActive());
                order.setSubTotal(orderDTO.getSubTotal());
                order.setTotalDiscount(orderDTO.getTotalDiscount());
                order.setTotalPrice(orderDTO.getTotalPrice());
            }
            else throw new EmptyElementException("Datos nulos");
            //Esto lo tengo que modificar
            Optional<Customer> optionalCustomer = Optional.empty();
            if (orderDTO.getCustomerID() != null) {
                if(orderDTO.getCustomerID() !=0){
                    optionalCustomer = customerRepository.findById(orderDTO.getCustomerID());
                    optionalCustomer.ifPresent(order::setCustomer);
                }
            }
            orderRepository.save(order);
            cargarItems(orderDTO, order);
            cargarImpuestos(orderDTO, order);
            return order.toOrderDTO();
        }
     throw new RuntimeException();

    }

    private void cargarImpuestos(OrderDTO orderDTO, Order order) {
        if(orderDTO.getCalculatedTaxDTOS() != null)
        {
            List<CalculatedTax> listCalculatedTaxes = new ArrayList<>();
            CalculatedTax calculatedTax = new CalculatedTax();
            for(int k = 0; k< orderDTO.getCalculatedTaxDTOS().size(); k++)
            {
                calculatedTax.setOrder(order);
                calculatedTax.setTax(taxRepository.findById(orderDTO.getCalculatedTaxDTOS().get(k).getTaxID()).get());
                calculatedTax.setTaxesAmount(orderDTO.getCalculatedTaxDTOS().get(k).getTaxesAmount());
                calculatedTaxRepository.save(calculatedTax);
                listCalculatedTaxes.add(calculatedTax);
            }
            order.setCalculatedTaxes(listCalculatedTaxes);
        }
    }

    private void cargarItems(OrderDTO orderDTO, Order order) {
        if(orderDTO.getItemDTO() != null && !orderDTO.getItemDTO().isEmpty()){
            order.setItems(orderDTO.getItemDTO().stream().map(ItemDTO::toItem).collect(Collectors.toList()));
            for (int j = 0; j < order.getItems().size(); j++){
                Item item = order.getItems().get(j);
                if(orderDTO.getItemDTO().get(j).getIdAsset() != null &&
                        assetRepository.existsById(orderDTO.getItemDTO().get(j).getIdAsset()))
                {
                    Asset asset = assetRepository.findById(orderDTO.getItemDTO().get(j).getIdAsset()).get();
                    item.setAsset(asset);
                    order.getItems().get(j).setOrder(order);
                }
                itemRepository.save(order.getItems().get(j));
            }
        }
    }

    private List<Item> buscarItems(List<ItemDTO> listItems)
    {
       if(listItems != null)
       {
           if(!listItems.isEmpty())
           {
               List<Item> listAsset = new ArrayList<>();
               for (ItemDTO listItem : listItems) {
                   Optional<Item> optionalAsset = itemRepository.findById(listItem.getIdItem());
                   optionalAsset.ifPresent(listAsset::add);
               }
               return listAsset;
           }
       }
       return null;
    }
    public List<OrderDTO> read()
    {
        return orderRepository.findAll().stream().map(Order::toOrderDTO).collect(Collectors.toList());
    }
    public OrderDTO update(  OrderDTO orderDTO) {
        Optional<Order> aux = orderRepository.findById(orderDTO.getIdOrder());
        if (aux.isPresent()) {
            if (orderDTO.getDateOrder() != null)
                aux.get().setDatesOrder(orderDTO.getDateOrder());
            if (orderDTO.getTotalPrice() != null && orderDTO.getTotalPrice().intValue() != 0)
                aux.get().setTotalPrice(orderDTO.getTotalPrice());
            if (orderDTO.getSubTotal() != null && orderDTO.getSubTotal().intValue() != 0)
                aux.get().setSubTotal(orderDTO.getSubTotal());
            if (orderDTO.getTotalDiscount() != null && orderDTO.getTotalDiscount().intValue() != 0)
                aux.get().setTotalDiscount(orderDTO.getTotalDiscount());
            if (orderDTO.getCustomerID() != null) {
                Optional<Customer> optionalCustomer = customerRepository.findById(orderDTO.getCustomerID());
                if (optionalCustomer.isPresent()) {
                    aux.get().setCustomer(optionalCustomer.get());
                }
                return orderRepository.save(aux.get()).toOrderDTO();
            }
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
    private boolean  checkEmpty(OrderDTO order)
    {
        if(order.getDateOrder() != null)
        {
            if(order.getTotalPrice() != null){
                if(order.getTotalDiscount() !=null)
                {
                    return order.getSubTotal() != null;
                }
            }
        }

        return false;
    }
}
