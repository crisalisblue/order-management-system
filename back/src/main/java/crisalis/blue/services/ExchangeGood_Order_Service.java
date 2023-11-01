package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.ExchangeGood_Order;
import crisalis.blue.repositories.ExchangeGood_Order_Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExchangeGood_Order_Service {
    private final ExchangeGood_Order_Repository exchangeGoodOrderRepository;

    public ExchangeGood_Order_Service(ExchangeGood_Order_Repository exchangeGoodOrderRepository)
    {
        this.exchangeGoodOrderRepository = exchangeGoodOrderRepository;
    }
    public ExchangeGood_Order create(ExchangeGood_Order exchangeGoodOrder)
    {
        if(checkIsEmpty(exchangeGoodOrder))
            return exchangeGoodOrderRepository.save(exchangeGoodOrder);
        else throw new EmptyElementException("Campos vacios");
    }
    public List<ExchangeGood_Order> read()
    {
        return exchangeGoodOrderRepository.findAll().stream().collect(Collectors.toList());
    }
    public ExchangeGood_Order update(ExchangeGood_Order exchangeGoodOrder)
    {
        Optional<ExchangeGood_Order> aux = exchangeGoodOrderRepository.
                findById(exchangeGoodOrder.getId_ExchangeGood_Customer());
        if(aux.isPresent())
        {
            if(!exchangeGoodOrder.getDescription_exchangeGood().isEmpty())
                aux.get().setDescription_exchangeGood(exchangeGoodOrder.getDescription_exchangeGood());
            if(exchangeGoodOrder.getExchangeGood_amount() != 0)
                aux.get().setExchangeGood_amount(exchangeGoodOrder.getExchangeGood_amount());
            if(exchangeGoodOrder.getDiscount_amount() != 0)
                aux.get().setAmount(exchangeGoodOrder.getAmount());
            if(exchangeGoodOrder.getId_Order() != null)
                aux.get().setId_Order(exchangeGoodOrder.getId_Order());
            if(exchangeGoodOrder.getId_ExchangeGood() != null)
                aux.get().setId_ExchangeGood(exchangeGoodOrder.getId_ExchangeGood());
            return aux.get();
        }
        throw new EmptyElementException("El id no existe");
    }
    public void delete(Long id_ExchangeGood_Customer)
    {
        if(exchangeGoodOrderRepository.findById(id_ExchangeGood_Customer).isPresent())
            exchangeGoodOrderRepository.deleteById(id_ExchangeGood_Customer);
        else
            throw new EmptyElementException("El id no existe");
    }

    private boolean checkIsEmpty(ExchangeGood_Order exchangeGoodOrder) {
        if(exchangeGoodOrder.getId_Order() != null)
            if(!exchangeGoodOrder.getDescription_exchangeGood().isEmpty()){
                if(exchangeGoodOrder.getExchangeGood_amount() !=0.0)
                {
                    if(exchangeGoodOrder.getAmount() !=0 )
                    {
                        if(exchangeGoodOrder.getId_ExchangeGood() != null)
                        {
                            if(exchangeGoodOrder.getDiscount_amount() != 0)
                                if(exchangeGoodOrder.getTaxes_applied() != 0)
                                {
                                    return true;
                                }
                        }
                    }
                }
            }
        return false;
    }
}
