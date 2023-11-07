package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.Asset;
import crisalis.blue.models.Item;
import crisalis.blue.models.Order;
import crisalis.blue.models.dto.ItemDTO;
import crisalis.blue.repositories.AssetRepository;
import crisalis.blue.repositories.ItemRepository;
import crisalis.blue.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final AssetRepository assetRepository;

    public ItemService(ItemRepository exchangeGoodOrderRepository,OrderRepository orderRepository,
                       AssetRepository assetRepository)
    {
        this.itemRepository = exchangeGoodOrderRepository;
        this.orderRepository = orderRepository;
        this.assetRepository = assetRepository;
    }
    public ItemDTO create(ItemDTO itemDTO)
    {
        Item item = new Item();
        if(checkIsEmpty(itemDTO)) {
            item.setWarrantyYears(itemDTO.getWarrantyYears());
            item.setItemPrice(itemDTO.getItemPrice());
            item.setItemDetails(itemDTO.getItemDitails());
            item.setTotalPrice(itemDTO.getTotalPrice());
            item.setItemQuantity(itemDTO.getItemQuantity());
            item.setDiscountAmount(itemDTO.getDiscountAmount());
            if (itemDTO.getIdOrder() != null) {
                Optional<Order> optionalOrder = orderRepository.findById(itemDTO.getIdOrder());
                if (optionalOrder.isPresent())
                    item.setOrder(optionalOrder.get());
            }
            if (itemDTO.getIdAsset() != null) {
                Optional<Asset> optionalAsset = assetRepository.findById(itemDTO.getIdAsset());
                if (optionalAsset.isPresent())
                    item.setAsset(optionalAsset.get());
            }
            return itemRepository.save(item).toItemDTO();
        }else throw new EmptyElementException("Campos vacios");
    }
    public List<ItemDTO> read()
    {
        return itemRepository.findAll().stream().map(Item::toItemDTO).collect(Collectors.toList());
    }
    public ItemDTO update(ItemDTO item)

    {
        Optional<Item> optionalItem = itemRepository.
                findById(item.getIdItem());
        if(optionalItem.isPresent())
        {
            //Id propio
            if(item.getIdItem() != 0)
                optionalItem.get().setId(item.getIdItem());
            // Detalles
            if(!item.getItemDitails().isEmpty())
                optionalItem.get().setItemDetails(item.getItemDitails());
            // Precio total
            if(item.getTotalPrice().intValue() != 0)
                optionalItem.get().setTotalPrice(item.getTotalPrice());
            // Monto descuento
            if(item.getDiscountAmount().intValue() != 0)
                optionalItem.get().setDiscountAmount(item.getDiscountAmount());
            // El id de la orden
            if(item.getIdOrder() != 0) {
                Optional<Order> optionalOrder = orderRepository.findById(item.getIdOrder());
                if(optionalOrder.isPresent())
                    optionalItem.get().setOrder(optionalOrder.get());
            }
            // El id del item
            if(item.getIdAsset() != 0) {
                Optional<Asset> optinalAsset = assetRepository.findById(item.getIdAsset());
                if(optinalAsset.isPresent())
                    optionalItem.get().setAsset(optinalAsset.get());
            }
            // Precio item
            if(item.getItemPrice().intValue() != 0)
                optionalItem.get().setItemPrice(item.getItemPrice());
            // Cantidad del item
            if(item.getItemQuantity() != 0)
                optionalItem.get().setItemQuantity(item.getItemQuantity());
            if(item.getWarrantyYears() != 0)
                optionalItem.get().setWarrantyYears(item.getWarrantyYears());
            return itemRepository.save(optionalItem.get()).toItemDTO();
        }
        throw new EmptyElementException("El id no existe");
    }
    public void delete(Long id_ExchangeGood_Customer)
    {
        if(itemRepository.findById(id_ExchangeGood_Customer).isPresent())
            itemRepository.deleteById(id_ExchangeGood_Customer);
        else
            throw new EmptyElementException("El id no existe");
    }

    private boolean checkIsEmpty(ItemDTO item) {
            if(!item.getItemDitails().isEmpty()){
                if(item.getItemQuantity() !=0)
                {
                        if(item.getItemPrice().intValue() != 0 )
                        {
                            if(item.getDiscountAmount().intValue() != 0)
                                if(item.getTotalPrice().intValue() != 0)
                                {
                                    return true;
                                }
                        }
                    }
                }

        return false;
    }
}
