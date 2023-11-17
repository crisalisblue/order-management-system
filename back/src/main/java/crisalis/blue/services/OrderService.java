package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.exceptions.custom.ResourceNotFoundException;
import crisalis.blue.models.*;
import crisalis.blue.models.dto.CalculatedTaxDTO;
import crisalis.blue.models.dto.ItemDTO;
import crisalis.blue.models.dto.OrderDTO;
import crisalis.blue.models.dto.TaxDTO;
import crisalis.blue.repositories.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    private final AssetRepository assetRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
            ItemRepository itemRepository,
            CalculatedTaxRepository calculatedTaxRepository, AssetRepository assetRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.calculatedTaxRepository = calculatedTaxRepository;
        this.assetRepository = assetRepository;
    }

    public OrderDTO create(OrderDTO orderDTO) {
        if (orderDTO != null) {
            Order order = new Order();
            if (checkEmpty(orderDTO)) {
                order.setDatesOrder(orderDTO.getDateOrder());
                order.setActive(orderDTO.getActive());
                order.setSubTotal(orderDTO.getSubTotal());
                order.setTotalDiscount(orderDTO.getTotalDiscount());
                order.setTotalPrice(orderDTO.getTotalPrice());
            } else
                throw new EmptyElementException("Datos nulos");
            // Esto lo tengo que modificar
            Optional<Customer> optionalCustomer = Optional.empty();
            if (orderDTO.getCustomerID() != null) {
                if (orderDTO.getCustomerID() != null && !BigDecimal.ZERO.equals(orderDTO.getCustomerID())) {
                    optionalCustomer = customerRepository.findById(orderDTO.getCustomerID());
                    optionalCustomer.ifPresent(order::setCustomer);
                }
            }
            orderRepository.save(order);
            if (orderDTO.getItemDTO() != null && !orderDTO.getItemDTO().isEmpty()) {
                order.setItems(orderDTO.getItemDTO().stream().map(ItemDTO::toItem).collect(Collectors.toList()));
                for (int j = 0; j < order.getItems().size(); j++) {
                    Item item = order.getItems().get(j);
                    if (orderDTO.getItemDTO().get(j).getIdAsset() != null) {
                        Asset asset = assetRepository.findById(orderDTO.getItemDTO().get(j).getIdAsset()).get();
                        item.setAsset(asset);
                    }
                    order.getItems().get(j).setIdOrder(order);
                    itemRepository.save(order.getItems().get(j));
                }
            }
            orderRepository.save(order);
            return order.toOrderDTO();
        }
        throw new RuntimeException();

    }

    private List<Item> buscarItems(List<ItemDTO> listItems) {
        if (listItems != null) {
            if (!listItems.isEmpty()) {
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

    public List<OrderDTO> read() {
        return orderRepository.findAll().stream().map(Order::toOrderDTO).collect(Collectors.toList());
    }

    public OrderDTO update(OrderDTO orderDTO) {
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

    public void delete(Long id) {
        Optional<Order> aux = orderRepository.findById(id);
        if (aux.isPresent()) {
            orderRepository.deleteById(id);
        } else
            throw new EmptyElementException("No se encontro el registro con ese id ");

    }

    private boolean checkEmpty(OrderDTO order) {
        if (order.getDateOrder() != null) {
            if (order.getTotalPrice() != null) {
                if (order.getTotalDiscount() != null) {
                    return order.getSubTotal() != null;
                }
            }
        }

        return false;
    }

    public OrderDTO getOrderById(Long id) {
        return this.orderRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Orden no encontrada"))
                .toOrderDTO();
    }
}
