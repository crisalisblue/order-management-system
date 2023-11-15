package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.*;
import crisalis.blue.models.dto.CalculatedTaxDTO;
import crisalis.blue.models.dto.ItemDTO;
import crisalis.blue.models.dto.OrderDTO;
import crisalis.blue.repositories.*;
import org.springframework.stereotype.Service;

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
            checkEmpty(orderDTO );
            order.setDatesOrder(orderDTO.getDateOrder());
            order.setActive(orderDTO.getActive());
            order.setSubTotal(orderDTO.getSubTotal());
            order.setTotalDiscount(orderDTO.getTotalDiscount());
            order.setTotalPrice(orderDTO.getTotalPrice());
            Optional<Customer> optionalCustomer = Optional.empty();
            optionalCustomer = customerRepository.findById(orderDTO.getCustomerID());
            optionalCustomer.ifPresent(order::setCustomer);
            orderRepository.save(order);
            createItems(orderDTO, order);
            crearImpuestos(orderDTO, order);
            return order.toOrderDTO();
        }
     throw new RuntimeException();

    }

    private void crearImpuestos(OrderDTO orderDTO, Order order) {
        if(orderDTO.getCalculatedTaxDTOS() != null)
        {
            List<CalculatedTax> listCalculatedTaxes = order.getCalculatedTaxes();
            CalculatedTax calculatedTax = new CalculatedTax();
            for(int k = 0; k< orderDTO.getCalculatedTaxDTOS().size(); k++)
            {

                createCalculatedTax(orderDTO.getCalculatedTaxDTOS().get(k), order, calculatedTax);
                listCalculatedTaxes.add(calculatedTax);
            }
            order.setCalculatedTaxes(listCalculatedTaxes);
        }
        else
            throw new EmptyElementException("Lista de calculatedTax vacias");
    }

    private void createCalculatedTax(CalculatedTaxDTO calculatedTaxDTO, Order order, CalculatedTax calculatedTax ) {
        calculatedTax.setOrder(order);
        calculatedTax.setTax(taxRepository.findById(calculatedTaxDTO.getTaxID()).get());
        calculatedTax.setTaxesAmount(calculatedTaxDTO.getTaxesAmount());
        calculatedTaxRepository.save(calculatedTax);
    }

    private void createItems(OrderDTO orderDTO, Order order) {
        if(orderDTO.getItemDTO() != null && !orderDTO.getItemDTO().isEmpty()){
            order.setItems(orderDTO.getItemDTO().stream().map(ItemDTO::toItem).collect(Collectors.toList()));
            List<ItemDTO> listItemDTO = orderDTO.getItemDTO();
            List<Item> listItem = order.getItems();
            for (int j = 0; j < order.getItems().size(); j++){
                asignarAssetAItem(listItemDTO.get(j), listItem.get(j));
                itemRepository.save(order.getItems().get(j));
                }
            order.setItems(listItem);
            }
        }

    private void asignarAssetAItem(ItemDTO itemDTO, Item item) {
        if(itemDTO.getIdAsset() != null &&
                assetRepository.existsById(itemDTO.getIdAsset())) {
            Asset asset = assetRepository.findById(itemDTO.getIdAsset()).get();
            item.setAsset(asset);
        }
}

    private List<Item> buscarItems(List<ItemDTO> listItems)
    {
       if(listItems != null && !listItems.isEmpty())
       {
           List<Item> listAsset = new ArrayList<>();
           for (ItemDTO listItem : listItems) {
               Optional<Item> optionalAsset = itemRepository.findById(listItem.getIdItem());
               optionalAsset.ifPresent(listAsset::add);
           }
           return listAsset;
       }
       throw new EmptyElementException("Lista de items vacia o nula ");
    }

   /* private List<CalculatedTax> actualizoCalculatedTax(OrderDTO orderDTO)
    {
        if(orderDTO.getCalculatedTaxDTOS() != null)
        {
            if(!listCalculatedTax.isEmpty())
            {
                List<CalculatedTax> listCalculated = new ArrayList<>();
                CalculatedTax calculatedTax = new CalculatedTax();
                for (int j=0; j<listCalculatedTax.size(); j++){
                    Optional<Tax> optionalTax = taxRepository.findById(listCalculatedTax.get(j).getTaxID());
                    if(optionalTax.isPresent())
                    {
                        calculatedTax.setTax(optionalTax.get());
                    }
                    if(listCalculatedTax.get(j).getTaxesAmount() != null)
                    {
                        calculatedTax.setTaxesAmount(listCalculatedTax.get(j).getTaxesAmount());
                    }
                    listCalculated.add(calculatedTax);
                }
                return listCalculated;
            }
        }
        return null;
    }*/
    public List<OrderDTO> read()
    {
        return orderRepository.findAll().stream().map(Order::toOrderDTO).collect(Collectors.toList());
    }
    public OrderDTO update(  OrderDTO orderDTO) {
        Optional<Order> optionalOrder = orderRepository.findById(orderDTO.getIdOrder());
        if (optionalOrder.isPresent()) {
            if (orderDTO.getDateOrder() != null)
                optionalOrder.get().setDatesOrder(orderDTO.getDateOrder());
            if (orderDTO.getTotalPrice() != null && orderDTO.getTotalPrice().intValue() != 0)
                optionalOrder.get().setTotalPrice(orderDTO.getTotalPrice());
            if (orderDTO.getSubTotal() != null && orderDTO.getSubTotal().intValue() != 0)
                optionalOrder.get().setSubTotal(orderDTO.getSubTotal());
            if (orderDTO.getTotalDiscount() != null && orderDTO.getTotalDiscount().intValue() != 0)
                optionalOrder.get().setTotalDiscount(orderDTO.getTotalDiscount());
            if (orderDTO.getCustomerID() != null) {
                Optional<Customer> optionalCustomer = customerRepository.findById(orderDTO.getCustomerID());
                if (optionalCustomer.isPresent()) {
                    optionalOrder.get().setCustomer(optionalCustomer.get());
                }
            }
            if(orderDTO.getItemDTO() !=null && !orderDTO.getItemDTO().isEmpty())
            {
                List<Item> listItem = buscarItems(orderDTO.getItemDTO());
                optionalOrder.get().setItems(listItem);
            }
            if(orderDTO.getCalculatedTaxDTOS() != null && !orderDTO.getCalculatedTaxDTOS().isEmpty())
            {
                List<CalculatedTaxDTO> listCalculatedDTO = orderDTO.getCalculatedTaxDTOS();
                List<CalculatedTax> listCalculatedTax = new ArrayList<>();
                for(int j =0; j< listCalculatedDTO.size();j++)
                {
                    CalculatedTax calculatedTax = new CalculatedTax();
                    calculatedTax.setId(listCalculatedDTO.get(j).getCalculatedTaxID());
                    calculatedTax.setOrder(optionalOrder.get());
                    calculatedTax.setTaxesAmount(listCalculatedDTO.get(j).getTaxesAmount());
                    calculatedTax.setTax(taxRepository.findById(listCalculatedDTO.get(j).getTaxID()).get());
                    listCalculatedTax.add(calculatedTax);
                }
                optionalOrder.get().setCalculatedTaxes(listCalculatedTax);
            }
            return orderRepository.save(optionalOrder.get()).toOrderDTO();
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
    private void  checkEmpty(OrderDTO order) throws EmptyElementException {
        if (order.getDateOrder() == null) {
            throw new EmptyElementException("Fecha de la orden vacia ");
        }
        if (order.getTotalPrice() == null) {
            throw new EmptyElementException("Total price de de la orden vacia ");
        }
        if (order.getTotalDiscount() != null)
                throw new EmptyElementException("Total discount de la orden vacia");
        if(order.getCustomerID() == null) {
            throw new EmptyElementException("El id del cliente vacio ");
        }
    }
}
