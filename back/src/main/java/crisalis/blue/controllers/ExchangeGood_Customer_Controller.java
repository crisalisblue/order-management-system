package crisalis.blue.controllers;

import crisalis.blue.models.ExchangeGood_Customer;
import crisalis.blue.services.ExchangeGood_Customer_Service;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExchangeGood_Customer_Controller {
    private ExchangeGood_Customer_Service exchangeGoodCustomerService;

    public ExchangeGood_Customer_Controller(ExchangeGood_Customer_Service exchangeGoodCustomerService)
    {
        this.exchangeGoodCustomerService = exchangeGoodCustomerService;
    }
    @PostMapping(value = "create",produces= MediaType.APPLICATION_JSON_VALUE)
    public ExchangeGood_Customer create(@RequestBody ExchangeGood_Customer exchangeGoodCustomer)
    {
        return exchangeGoodCustomerService.create(exchangeGoodCustomer);
    }
    @GetMapping(value = "read",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExchangeGood_Customer> read()
    {
        return exchangeGoodCustomerService.read();
    }
    @PutMapping(value="update",produces=MediaType.APPLICATION_JSON_VALUE)
    public ExchangeGood_Customer update(@RequestBody ExchangeGood_Customer exchangeGoodCustomer)
    {
        return exchangeGoodCustomerService.update(exchangeGoodCustomer);
    }
}
