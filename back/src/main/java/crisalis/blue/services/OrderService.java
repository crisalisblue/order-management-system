package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.exceptions.custom.NotCreatedException;
import crisalis.blue.exceptions.custom.ResourceNotFoundException;
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
    private final OrderEngineService orderEngineService;
    private final SubscriptionService subscriptionService;

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        ItemRepository itemRepository,
                        CalculatedTaxRepository calculatedTaxRepository,
                        AssetRepository assetRepository,
                        TaxRepository taxRepository, OrderEngineService orderEngineerService,
                        SubscriptionService subscriptionService)
    {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
        this.calculatedTaxRepository = calculatedTaxRepository;
        this.assetRepository = assetRepository;
        this.taxRepository = taxRepository;
        this.orderEngineService = orderEngineerService;
        this.subscriptionService = subscriptionService;
    }

    public OrderDTO create(OrderDTO orderDTO) {
        if (orderDTO != null) {
            checkEmpty(orderDTO);
            Order order = new Order(orderDTO); //Esto hay que charlarlo y tomar la mejor desición
            asignarCustomerAOrder(orderDTO, order);

            //Compruebo si ya existe una Subscripcion para todos los assets dado
            for (ItemDTO asset : orderDTO.getItemDTO()){
                Subscription subComprobation = subscriptionService.getSubscriptionByAssetIdAndCustomerId(asset.getIdAsset(), orderDTO.getCustomerID());
                if (subComprobation != null) {
                    throw new NotCreatedException("El customer: " +orderDTO.getCustomerName()+ " Ya tiene el asset: " +asset.getNameAsset()+ " Asignado");
                }
            }
            order = orderRepository.save(order);
            asignarAssetsAItems(order.getItems(),orderDTO.getItemDTO());
            crearSubscripcion(order);
            orderRepository.save(order);
            orderEngineService.calculateOrderTotals(order);
            return order.toOrderDTO();
        }
        throw new RuntimeException();

    }
    private void asignarAssetsAItems(List<Item> listItems, List<ItemDTO>listDTO) throws EmptyElementException
    {
        if(listDTO == null && listItems == null)
        {
            throw new  EmptyElementException("La lista de itemDTO es nula o la lista de items en el order es nula");
        }
        else
        {
            for(int j=0; j<listDTO.size(); j++)
            {
                    listItems.get(j).setAsset(assetRepository.findById(listDTO.get(j).getIdAsset()).get());
            }
        }
    }
    private void crearSubscripcion(Order order){
        for(Item item : order.getItems()){
            if (item.getAsset() instanceof Servicie) {
                Subscription newSubscription = new Subscription();
                newSubscription.setCustomer(order.getCustomer());
                newSubscription.setAsset(item.getAsset());
                newSubscription.setStatus(Boolean.TRUE);
                subscriptionService.createSubscription(newSubscription.toDTO());
            }
        }
    }
    private void asignarCustomerAOrder(OrderDTO orderDTO, Order order) {
        Optional<Customer> optionalCustomer = Optional.empty();
        optionalCustomer = customerRepository.findById(orderDTO.getCustomerID());
        optionalCustomer.ifPresent(order::setCustomer);
    }
    private void asignarOrderAListCalculated(List<CalculatedTax> listCalculated, Order order) {
        for (int j = 0; j < listCalculated.size(); j++) {
            listCalculated.get(j).setOrder(order);
        }
    }
    private void asignarAssetAItem(ItemDTO itemDTO, Item item) {
        if (itemDTO.getIdAsset() != null &&
                assetRepository.existsById(itemDTO.getIdAsset())) {
            Asset asset = assetRepository.findById(itemDTO.getIdAsset()).get();
            item.setAsset(asset);
        }
    }
    public List<OrderDTO> read() {
        return orderRepository.findAll().stream().map(Order::toOrderDTO).collect(Collectors.toList());
    }
    public OrderDTO update(OrderDTO orderDTO) {
        Optional<Order> optionalOrder = orderRepository.findById(orderDTO.getIdOrder());
        if (optionalOrder.isPresent()) {
            checkEmpty(orderDTO);
            actualizarPrimitivos(optionalOrder.get(), orderDTO);
            optionalOrder.get().setItems(updateItems(orderDTO.getItemDTO()));
            return orderRepository.save(optionalOrder.get()).toOrderDTO();
        }
        throw new EmptyElementException("La entrada que se quiere actualizarn o existe");
    }

    private List<CalculatedTax> listCalculatedTaxToCalculatedTaxDTO(List<CalculatedTaxDTO> listCTD) {
        if (listCTD != null && !listCTD.isEmpty()) {
            List<CalculatedTax> listCT = new ArrayList<>();
            CalculatedTax ct = null;
            for (int j = 0; j < listCTD.size(); j++) {
                ct = (buscarOCrearCT(listCTD.get(j)));
                actulizarRelacionesCalculatedTax(ct, listCTD.get(j).getTaxID());
                listCT.add(ct);
            }
            return listCT;
        } else {
            throw new EmptyElementException("Lista de CalculatedTaxDTO vacia");
        }
    }

    private CalculatedTax buscarOCrearCT(CalculatedTaxDTO ctd) {
        Optional<CalculatedTax> optionalCT = calculatedTaxRepository.findById(ctd.getCalculatedTaxID());
        if (optionalCT.isPresent()) {
            optionalCT.get().setTaxesAmount(ctd.getTaxesAmount());
            return optionalCT.get();
        } else {
            return new CalculatedTax(ctd);
        }
    }

    private void actulizarRelacionesCalculatedTax(CalculatedTax ct, Long idTax) {
        if (idTax != null && idTax != 0) {
            Optional<Tax> optionalTax = taxRepository.findById(idTax);
            if (optionalTax.isPresent()) {
                ct.setTax(optionalTax.get());
            } else
                throw new EmptyElementException("No existe el tax que se quiso asignar a la orden ");
        } else
            throw new EmptyElementException("El id ingresado es invalido ");
    }

    private void actualizarPrimitivos(Order order, OrderDTO orderDTO) {
        order.setDatesOrder(orderDTO.getDateOrder());
        order.setActive(orderDTO.getActive());
        order.setTotalDiscount(orderDTO.getTotalDiscount());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setSubTotal(orderDTO.getSubTotal());
    }

    private List<Item> updateItems(List<ItemDTO> itemDTOList) {
        List<Item> listItem = new ArrayList<>();
        for (int j = 0; j < itemDTOList.size(); j++) {
            Item item = buscarOCrearItem(itemDTOList.get(j));
            if (item.getId() != null)
                updateItem(item, itemDTOList.get(j));
            updateItem(item, itemDTOList.get(j));
            asignarAssetAItem(itemDTOList.get(j), item);
            listItem.add(item);
        }
        return listItem;
    }

    private Item buscarOCrearItem(ItemDTO itemDTO) {
        if (itemDTO != null) {
            if (itemDTO.getIdItem() != null) {
                Optional<Item> optinalItem = itemRepository.findById(itemDTO.getIdItem());
                if (optinalItem.isPresent())
                    return optinalItem.get();
            } else
                return itemRepository.save(new Item(itemDTO));
        } else {
            throw new EmptyElementException("Item dto es nulo ");
        }
    return null;
    }

    private void updateItem(Item item, ItemDTO itemDTO) {
        item.setItemDetails(itemDTO.getItemDitails());
        item.setItemPrice(itemDTO.getItemPrice());
        item.setItemQuantity(itemDTO.getItemQuantity());
        item.setId(itemDTO.getIdItem());
        item.setTotalPrice(itemDTO.getTotalPrice());
        item.setWarrantyYears(itemDTO.getWarrantyYears());
        item.setDiscountAmount(itemDTO.getDiscountAmount());
    }

    public void delete(Long id) {
        Optional<Order> aux = orderRepository.findById(id);
        if (aux.isPresent()) {
            orderRepository.deleteById(id);
        } else
            throw new EmptyElementException("No se encontro el registro con ese id ");

    }

    private void checkEmpty(OrderDTO order) throws EmptyElementException {
        if (order.getDateOrder() == null) {
            throw new EmptyElementException("Fecha de la orden vacia ");
        }
        if (order.getItemDTO() == null || order.getItemDTO().isEmpty()) {
            throw new EmptyElementException("La lista de ítems esta vacia");
        }
        if (order.getCustomerID() == null) {
            throw new EmptyElementException("El id del cliente vacio ");
        }
    }

    public OrderDTO getOrderById(Long id) {
        return this.orderRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Orden no encontrada"))
                .toOrderDTO();
    }

    // CREAR FUNCION QUE REFRESQUE LA INFORMACION
    public OrderDTO refresh(OrderDTO orderDTO) {
        Order order = new Order(orderDTO);
        actualizarPrimitivos(order,orderDTO);
        order.setItems(updateItems(orderDTO.getItemDTO()));
    if ("calculate".equals(orderDTO.getAction())) {
             orderEngineService.calculateOrderTotals(order);
        } else if ("customer".equals(orderDTO.getAction())) {
            updateCustomerInfo(orderDTO,order);
        }
        return order.toOrderDTO();
    }



    private void updateCustomerInfo(OrderDTO orderDTO, Order order) {
        if (orderDTO.getCustomerID() != null) {
            Optional<Customer> optionalCustomer = customerRepository.findById(orderDTO.getCustomerID());
            if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();
                order.setCustomer(customer);
            }
        }
    }
}
