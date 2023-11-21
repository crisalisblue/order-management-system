package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.exceptions.custom.ResourceNotFoundException;
import crisalis.blue.models.*;
import crisalis.blue.models.dto.CalculatedTaxDTO;
import crisalis.blue.models.dto.ItemDTO;
import crisalis.blue.models.dto.OrderDTO;
import crisalis.blue.models.dto.TaxDTO;
import crisalis.blue.repositories.*;
import org.aspectj.weaver.ast.Or;
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

    public List<OrderDTO> read() {
        return orderRepository.findAll().stream().map(Order::toOrderDTO).collect(Collectors.toList());
    }
    public OrderDTO update(  OrderDTO orderDTO) {
        Optional<Order> optionalOrder = orderRepository.findById(orderDTO.getIdOrder());
        if (optionalOrder.isPresent()) {
            checkEmpty(orderDTO);
            actualizarPrimitivos(optionalOrder.get(),orderDTO);
            optionalOrder.get().setItems(updateItems(orderDTO.getItemDTO()));
            asignarOrderAListItems(optionalOrder.get().getItems(),optionalOrder.get());
            optionalOrder.get().setCalculatedTaxes(listCalculatedTaxToCalculatedTaxDTO(orderDTO.getCalculatedTaxDTOS()));
            asignarOrderAListCalculated(optionalOrder.get().getCalculatedTaxes(),optionalOrder.get());
            return orderRepository.save(optionalOrder.get()).toOrderDTO();
        }
        throw new EmptyElementException("La entrada que se quiere actualizarn o existe");
    }
    private List<CalculatedTax> listCalculatedTaxToCalculatedTaxDTO(List<CalculatedTaxDTO> listCTD)
    {
        if(listCTD != null && !listCTD.isEmpty())
        {
            List<CalculatedTax> listCT = new ArrayList<>();
            CalculatedTax ct = null;
            for(int j=0;j<listCTD.size();j++)
            {
                ct = (buscarOCrearCT(listCTD.get(j)));
                actulizarRelacionesCalculatedTax(ct,listCTD.get(j).getTaxID());
                listCT.add(ct);
            }
            return listCT;
        }
        else {
            throw new EmptyElementException("Lista de CalculatedTaxDTO vacia");
        }
    }

    private CalculatedTax buscarOCrearCT(CalculatedTaxDTO ctd)
    {
        Optional<CalculatedTax> optionalCT = calculatedTaxRepository.findById(ctd.getCalculatedTaxID());
        if(optionalCT.isPresent())
        {
            optionalCT.get().setTaxesAmount(ctd.getTaxesAmount());
            return optionalCT.get();
        }
        else
        {
            return new CalculatedTax(ctd);
        }
    }
    private void actulizarRelacionesCalculatedTax(CalculatedTax ct,Long idTax)
    {
        if(idTax != null && idTax !=0 )
        {
            Optional<Tax> optionalTax = taxRepository.findById(idTax);
            if(optionalTax.isPresent())
            {
                ct.setTax(optionalTax.get());
            }
            else throw new EmptyElementException("No existe el tax que se quiso asignar a la orden ");
        }
        else throw new EmptyElementException("El id ingresado es invalido ");
    }
    private void actualizarPrimitivos(Order order, OrderDTO orderDTO)
    {
        order.setDatesOrder(orderDTO.getDateOrder());
        order.setActive(orderDTO.getActive());
        order.setTotalDiscount(orderDTO.getTotalDiscount());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setSubTotal(orderDTO.getSubTotal());
    }

    private List<Item> updateItems( List<ItemDTO> itemDTOList)
    {
        List<Item> listItem = new ArrayList<>();
        for(int j=0;j<itemDTOList.size();j++)
        {
            Item item = buscarOCrearItem(itemDTOList.get(j));
            if(item.getId() != null)
                updateItem(item,itemDTOList.get(j));
            updateItem(item,itemDTOList.get(j));
            asignarAssetAItem(itemDTOList.get(j),item);
            listItem.add(item);
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
            return itemRepository.save(new Item(itemDTO));
    }
    private void updateItem(Item item,ItemDTO itemDTO)
    {
        item.setItemDetails(itemDTO.getItemDitails());
        item.setItemPrice(itemDTO.getItemPrice());
        item.setItemQuantity(itemDTO.getItemQuantity());
        item.setId(itemDTO.getIdItem());
        item.setTotalPrice(itemDTO.getTotalPrice());
        item.setWarrantyYears(itemDTO.getWarrantyYears());
        item.setDiscountAmount(itemDTO.getDiscountAmount());
    }
    public void delete(Long id )
    {
        Optional<Order> aux = orderRepository.findById(id);
        if (aux.isPresent()) {
            orderRepository.deleteById(id);
        } else
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

    public OrderDTO getOrderById(Long id) {
        return this.orderRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Orden no encontrada"))
                .toOrderDTO();
    }
}
