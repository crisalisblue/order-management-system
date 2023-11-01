package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.ExchangeGood_Customer;
import crisalis.blue.repositories.ExchangeGood_Customer_Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExchangeGood_Customer_Service {
    private final ExchangeGood_Customer_Repository exchangeGoodCustomerRepository;

    public ExchangeGood_Customer_Service(ExchangeGood_Customer_Repository exchangeGoodCustomerRepository)
    {
        this.exchangeGoodCustomerRepository = exchangeGoodCustomerRepository;
    }
    public ExchangeGood_Customer create(ExchangeGood_Customer exchangeGoodCustomer)
    {
        if(checkIsEmpty(exchangeGoodCustomer))
            return exchangeGoodCustomerRepository.save(exchangeGoodCustomer);
        else throw new EmptyElementException("Campos vacios");
    }
    public List<ExchangeGood_Customer> read()
    {
        return exchangeGoodCustomerRepository.findAll().stream().collect(Collectors.toList());
    }
    public ExchangeGood_Customer update(ExchangeGood_Customer exchangeGoodCustomer)
    {
        Optional<ExchangeGood_Customer> aux = exchangeGoodCustomerRepository.
                findById(exchangeGoodCustomer.getId_ExchangeGood_Customer());
        if(aux.isPresent())
        {
            if(!exchangeGoodCustomer.getDescription_exchangeGood().isEmpty())
                aux.get().setDescription_exchangeGood(exchangeGoodCustomer.getDescription_exchangeGood());
            if(exchangeGoodCustomer.getExchangeGood_amount() != 0)
                aux.get().setExchangeGood_amount(exchangeGoodCustomer.getExchangeGood_amount());
            if(exchangeGoodCustomer.getDiscount_amount() != 0)
                aux.get().setAmount(exchangeGoodCustomer.getAmount());
            if(exchangeGoodCustomer.getId_Customer() != null)
                aux.get().setId_Customer(exchangeGoodCustomer.getId_Customer());
            if(exchangeGoodCustomer.getId_ExchangeGood() != null)
                aux.get().setId_ExchangeGood(exchangeGoodCustomer.getId_ExchangeGood());
            return aux.get();
        }
        throw new EmptyElementException("El id no existe");
    }
    public void delete(Long id_ExchangeGood_Customer)
    {
        if(exchangeGoodCustomerRepository.findById(id_ExchangeGood_Customer).isPresent())
            exchangeGoodCustomerRepository.deleteById(id_ExchangeGood_Customer);
        else
            throw new EmptyElementException("El id no existe");
    }

    private boolean checkIsEmpty(ExchangeGood_Customer exchangeGoodCustomer) {
        if(exchangeGoodCustomer.getId_Customer() != null)
            if(!exchangeGoodCustomer.getDescription_exchangeGood().isEmpty()){
                if(exchangeGoodCustomer.getExchangeGood_amount() !=0.0)
                {
                    if(exchangeGoodCustomer.getAmount() !=0 )
                    {
                        if(exchangeGoodCustomer.getId_ExchangeGood() != null)
                        {
                            if(exchangeGoodCustomer.getDiscount_amount() != 0)
                                if(exchangeGoodCustomer.getTaxes_applied() != 0)
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
