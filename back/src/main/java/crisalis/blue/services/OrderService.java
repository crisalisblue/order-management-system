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
            checkEmpty(orderDTO);
            Order order = new Order(orderDTO);
            asignarCustomerAOrder(orderDTO, order);
            orderRepository.save(order);
            order.setItems(createListItemDeItemDTO(orderDTO.getItemDTO()));
            asignarOrderAListItems(order.getItems(),order);
            order.setCalculatedTaxes(createCalculatedTaxToCalculatedTaxDTO(orderDTO.getCalculatedTaxDTOS()));
            asignarOrderAListCalculated(order.getCalculatedTaxes(),order);
            orderRepository.save(order);
            return order.toOrderDTO();
        }
     throw new RuntimeException();

    }

    private void asignarCustomerAOrder(OrderDTO orderDTO, Order order) {
        Optional<Customer> optionalCustomer = Optional.empty();
        optionalCustomer = customerRepository.findById(orderDTO.getCustomerID());
        optionalCustomer.ifPresent(order::setCustomer);
    }


    private List<CalculatedTax> createCalculatedTaxToCalculatedTaxDTO(List<CalculatedTaxDTO> listCalculatedDTO)
    {
        List<CalculatedTax> listCalculated = new ArrayList<>();
        CalculatedTax calculatedTax = new CalculatedTax();
        for(int j=0;j<listCalculatedDTO.size();j++)
        {
           createCalculatedTax(listCalculatedDTO.get(j),calculatedTax);
            listCalculated.add(calculatedTax);
        }
        return listCalculated;
    }
    private void createCalculatedTax(CalculatedTaxDTO calculatedTaxDTO, CalculatedTax calculatedTax ) {
        calculatedTax.setTax(taxRepository.findById(calculatedTaxDTO.getTaxID()).get());
        calculatedTax.setTaxesAmount(calculatedTaxDTO.getTaxesAmount());
    }
    private void asignarOrderAListCalculated(List<CalculatedTax>listCalculated,Order order)
    {
        for(int j=0;j<listCalculated.size();j++)
        {
            listCalculated.get(j).setOrder(order);
        }
    }

    private List<Item> createListItemDeItemDTO(List<ItemDTO> itemDTOList)
    {
        List<Item> listItem = new ArrayList<>();
        for(int j=0; j< itemDTOList.size();j++)
        {
            Item item = new Item(itemDTOList.get(j));
            asignarAssetAItem(itemDTOList.get(j),item);
            listItem.add(item);
        }
        return listItem;
    }


    private void asignarAssetAItem(ItemDTO itemDTO, Item item) {
        if(itemDTO.getIdAsset() != null &&
                assetRepository.existsById(itemDTO.getIdAsset())) {
            Asset asset = assetRepository.findById(itemDTO.getIdAsset()).get();
            item.setAsset(asset);
        }
    }
    private void asignarOrderAListItems(List<Item>list,Order order)
    {
        for(int j=0; j<list.size();j++)
        {
            list.get(j).setOrder(order);
        }
    }

    /*private List<Item> buscarItems(List<ItemDTO> listItems)
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
    }*/

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
            checkEmpty(orderDTO);
            Order order = new Order(orderDTO);
            asignarCustomerAOrder(orderDTO,order);
            order.setItems(updateItems(orderDTO.getItemDTO()));
            //order.setCalculatedTaxes(createCalculatedTaxToCalculatedTaxDTO(orderDTO.getCalculatedTaxDTOS()));
            asignarOrderAListItems(order.getItems(),order);
            asignarOrderAListCalculated(order.getCalculatedTaxes(),order);
            return order.toOrderDTO();
        }
        return null;
    }

    private List<Item> updateItems( List<ItemDTO> itemDTOList)
    {
        List<Item> listItem = new ArrayList<>();
        for(int j=0;j<itemDTOList.size();j++)
        {
            Item item = buscarOCrearItem(itemDTOList.get(j));

        }
        return listItem;
    }
    private Item buscarOCrearItem(ItemDTO itemDTO)
    {
        Optional<Item> optinalItem = itemRepository.findById(itemDTO.getIdItem());
        if(optinalItem.isPresent())
        {
            return optinalItem.get();
        }
        else
            return new Item(itemDTO);
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
        if (order.getTotalDiscount() == null)
                throw new EmptyElementException("Total discount de la orden vacia");
        if(order.getCustomerID() == null) {
            throw new EmptyElementException("El id del cliente vacio ");
        }
    }
}
