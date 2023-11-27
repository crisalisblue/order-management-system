package crisalis.blue.services;

import crisalis.blue.models.*;
import crisalis.blue.models.dto.ItemRefreshDTO;
import crisalis.blue.services.OrderService;
import crisalis.blue.models.dto.OrderRefreshDTO;
import crisalis.blue.repositories.AssetRepository;
import crisalis.blue.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderEngineerService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AssetRepository assetRepository;
    public  Order calculateOrderTotals(OrderRefreshDTO orderDTO) {
        Order order = new Order(orderDTO);
        if (orderDTO.getItemDTO() != null && !orderDTO.getItemDTO().isEmpty()) {
            List<Item> items = orderDTO.getItemDTO().stream()
                    .map(ItemRefreshDTO::toItem)
                    .collect(Collectors.toList());

            BigDecimal subtotal = BigDecimal.ZERO;
            BigDecimal total;
            BigDecimal discounts = BigDecimal.ZERO;

            for (int j = 0; j < items.size(); j++) {
                Item item = items.get(j);

                if (orderDTO.getItemDTO().get(j).getIdAsset() != null) {
                    Asset asset = assetRepository.findById(orderDTO.getItemDTO().get(j).getIdAsset()).orElse(null);
                    if (asset != null) {
                        item.setAsset(asset);
                        item.setItemPrice(asset.getBaseAmount());
                    }
                }

                BigDecimal itemPrice = item.getItemPrice() != null ? item.getItemPrice() : BigDecimal.ZERO;
                BigDecimal itemQuantity = BigDecimal.valueOf(item.getItemQuantity()) != null
                        ? BigDecimal.valueOf(item.getItemQuantity())
                        : BigDecimal.ZERO;

                subtotal = subtotal.add(itemPrice.multiply(itemQuantity));// Calcula el subtotal de de ítem
                aplicarImpuestos(item);

            }

        }


    }
    private void aplicarImpuestos(Item item) {
        Asset asset = item.getAsset();
        List<Tax> listTax = asset.getTaxList();
        BigDecimal valorImpuesto = BigDecimal.ZERO;
        Tax tax =null;
        for (int j = 0; j<listTax.size(); j++)
        {
            tax = listTax.get(j);
            valorImpuesto = valorImpuesto.add(item.getItemPrice().multiply(tax.getPercentage()));
        }
        item.getTotalPrice().add(valorImpuesto);
    }
    private void aplicarDescuento(Item item)
    {
        item.setDiscountAmount(item.getItemPrice().multiply(BigDecimal.valueOf(0.1)));
        item.setTotalPrice(item.getItemPrice().subtract(item.getDiscountAmount()));
    }
}
