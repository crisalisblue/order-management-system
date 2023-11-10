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
    private final AssetRepository assetRepository;

    public ItemService(ItemRepository exchangeGoodOrderRepository,
                       AssetRepository assetRepository)
    {
        this.itemRepository = exchangeGoodOrderRepository;
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
            item.setAsset(itemDTO.getAssetDTO().assetDTOtoAsset());
            item.setIdOrder(itemDTO.getOrderDTO().toOrder());
            return itemRepository.save(item).toItemDTO();
        }else throw new EmptyElementException("Campos vacios");
    }
    public List<ItemDTO> read()
    {
        return itemRepository.findAll().stream().map(Item::toItemDTO).collect(Collectors.toList());
    }
    public ItemDTO update(ItemDTO itemDTO)

    {
        Optional<Item> optionalItem = itemRepository.
                findById(itemDTO.getIdItem());
        if(optionalItem.isPresent()) {

            if (itemDTO.getIdItem() != 0)
                optionalItem.get().setId(itemDTO.getIdItem());

            if (!itemDTO.getItemDitails().isEmpty())
                optionalItem.get().setItemDetails(itemDTO.getItemDitails());

            if (itemDTO.getTotalPrice().intValue() != 0)
                optionalItem.get().setTotalPrice(itemDTO.getTotalPrice());
            if (itemDTO.getDiscountAmount().intValue() != 0)
                optionalItem.get().setDiscountAmount(itemDTO.getDiscountAmount());

            if (itemDTO.getAssetDTO() != null) {
                Optional<Asset> optinalAsset = assetRepository.findById(itemDTO.getAssetDTO().getId());
                if (optinalAsset.isPresent())
                    optionalItem.get().setAsset(optinalAsset.get());
                else
                    optionalItem.get().setAsset(null);
            }
            if (itemDTO.getItemPrice().intValue() != 0)
                optionalItem.get().setItemPrice(itemDTO.getItemPrice());

            if (itemDTO.getItemQuantity() != 0)
                optionalItem.get().setItemQuantity(itemDTO.getItemQuantity());
            if (itemDTO.getWarrantyYears() != 0)
                optionalItem.get().setWarrantyYears(itemDTO.getWarrantyYears());
            return itemRepository.save(optionalItem.get()).toItemDTO();
        } else
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
                            if(item.getDiscountAmount().intValue() != 0){
                                if(item.getAssetDTO() != null) {
                                    return item.getTotalPrice().intValue() != 0;
                                }
                            }

                        }
                    }
                }

        return false;
    }
}
