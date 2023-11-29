package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.*;
import crisalis.blue.repositories.AssetRepository;
import crisalis.blue.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderEngineService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AssetRepository assetRepository;
    public  void calculateOrderTotals(Order order) throws EmptyElementException {
        // Tomo los ítems del pedido
        List<Item> listItem = order.getItems();
        // Creo una variable item auxiliar
        Item item = null;
        // Creo un total auxiliar
        //BigDecimal auxTotal= BigDecimal.ZERO;
        // Creo un boolean para saber si tengo que aplicar descuento a los items que son productos
        boolean descuento = siAplicaDescuento(order);
        if(listItem != null)
        {
            // Recorro la lista de ítems
            for(int j=0; j<listItem.size();j++)
            {
                item = listItem.get(j);
                item.setItemPrice(item.getItemPrice() != null ? item.getItemPrice() : BigDecimal.ZERO);
                item.setSupportFee( item.getAsset() instanceof Servicie ?
                        ((Servicie)item.getAsset()).getSupportFee() : BigDecimal.ZERO);
                item.setItemQuantity(item.getItemQuantity() == 0? 1: item.getItemQuantity());
                BigDecimal suma = item.getItemPrice()
                .multiply(BigDecimal.valueOf(item.getItemQuantity()))
                                .add(item.getSupportFee())
                                .add(item.getWarrantyPrice().multiply(BigDecimal.valueOf(item.getWarrantyYears())));// Calcula el subtotal de de ítem
                if(descuento) {
                    aplicarDescuento(item);
                }
                item.setTotalPrice(suma);
                order.getSubTotal().add(item.getTotalPrice());
            }
            aplicarImpuestos(order);
            calcularTotalPriceOrder(order);
        }
        else throw new EmptyElementException("La lista de ítem enviada en la orden esta vacia ");
    }

    private boolean  siAplicaDescuento(Order order)
    {
        Asset response = null;
        List<Item> listItem = order.getItems();
        // Si tiene una subcripción activa aplico el descuento
            //Me tiene que devolver el asset que encontro
        // Tegno que guardar el servicio que habilito el descuento
        if(response == null)
        {
            for (int j=0; j< listItem.size();j++ )
            {
                if(listItem.get(j).getAsset() instanceof  Servicie)
                {
                    response= listItem.get(j).getAsset();
                    break;
                }
            }
        }
         order.setAssetSuscription(response); // le agrego el servicio que habilito el descuento
         return !(response == null);
    }
    private void aplicarDescuento(Item item)
    {
        item.setDiscountAmount(item.getItemPrice().multiply(BigDecimal.valueOf(0.1)));
        item.setTotalPrice(item.getItemPrice().subtract(item.getDiscountAmount()));
    }

    private void aplicarImpuestos(Order order) {
        // Tomo la lista de los ítems
        List<Item> listItem = order.getItems();
        // Recorro la lista de los ítems
        for(Item item:listItem)
        {
            // Del producto o servicio del ítem tomo la lista de impuestos aplicado que tiene
            List<Tax>listTax = item.getAsset().getTaxList();
            // Recorro la lista de impuestos del producto o servicio
            for(int k=0; k<listTax.size();k++)
            {

                // tomo el impuesto de la lista
                Tax tax = listTax.get(k);
                // Me fijo si ya existe un registro que relacione el impuesto con este pedido
                CalculatedTax calculatedTax = buscarOrderTax(tax,order.getCalculatedTaxes());
                // Si no existe lo creo y lo agrego a la lista de calculatedTax de order
                if(calculatedTax == null)
                {
                    calculatedTax = new CalculatedTax(order,tax);
                    order.getCalculatedTaxes().add(calculatedTax);
                }
                // Sumo a la item taxesAmount el monto que se le agrega al  aplicar el impuesto a este ítem
                calculatedTax.setTaxesAmount(calculatedTax.getTaxesAmount().
                        add(item.getTotalPrice().multiply(tax.getPercentage().divide(BigDecimal.valueOf(100)))));
                order.getCalculatedTaxes().add(calculatedTax);
            }
        }

    }
    private CalculatedTax buscarOrderTax(Tax tax, List<CalculatedTax>listCT)
    {
        if(listCT != null) {
            for (int j = 0; j < listCT.size(); j++) {
                if (listCT.get(j).getTax().getName().equals(tax.getName()))
                    return listCT.get(j);
            }
        }
        return null;
    }
    private void calcularTotalPriceOrder(Order order)
    {
        List<CalculatedTax> listCT = order.getCalculatedTaxes();
        order.setTotalPrice(order.getSubTotal());
        if(listCT != null)
        {
            for(int j=0; j<listCT.size();j++) {
                order.setTotalPrice(order.getTotalPrice().add(listCT.get(j).getTaxesAmount()));
            }
        }
    }
}
