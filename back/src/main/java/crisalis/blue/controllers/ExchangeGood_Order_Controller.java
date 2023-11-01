package crisalis.blue.controllers;

import crisalis.blue.models.ExchangeGood_Order;
import crisalis.blue.services.ExchangeGood_Order_Service;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExchangeGood_Order_Controller {
    private ExchangeGood_Order_Service exchangeGoodOrderService;

    public ExchangeGood_Order_Controller(ExchangeGood_Order_Service exchangeGoodOrderService)
    {
        this.exchangeGoodOrderService = exchangeGoodOrderService;
    }
    @PostMapping(value = "create",produces= MediaType.APPLICATION_JSON_VALUE)
    public ExchangeGood_Order create(@RequestBody ExchangeGood_Order exchangeGoodOrder)
    {
        return exchangeGoodOrderService.create(exchangeGoodOrder);
    }
    @GetMapping(value = "read",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExchangeGood_Order> read()
    {
        return exchangeGoodOrderService.read();
    }
    @PutMapping(value="update",produces=MediaType.APPLICATION_JSON_VALUE)
    public ExchangeGood_Order update(@RequestBody ExchangeGood_Order exchangeGoodOrder)
    {
        return exchangeGoodOrderService.update(exchangeGoodOrder);
    }
}
