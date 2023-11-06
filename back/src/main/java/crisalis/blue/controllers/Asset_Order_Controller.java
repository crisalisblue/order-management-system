package crisalis.blue.controllers;

import crisalis.blue.models.Asset_Order;
import crisalis.blue.services.Asset_Order_Service;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Asset_Order_Controller {
    private Asset_Order_Service exchangeGoodOrderService;

    public Asset_Order_Controller(Asset_Order_Service exchangeGoodOrderService)
    {
        this.exchangeGoodOrderService = exchangeGoodOrderService;
    }
    @PostMapping(value = "create",produces= MediaType.APPLICATION_JSON_VALUE)
    public Asset_Order create(@RequestBody Asset_Order exchangeGoodOrder)
    {
        return exchangeGoodOrderService.create(exchangeGoodOrder);
    }
    @GetMapping(value = "read",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Asset_Order> read()
    {
        return exchangeGoodOrderService.read();
    }
    @PutMapping(value="update",produces=MediaType.APPLICATION_JSON_VALUE)
    public Asset_Order update(@RequestBody Asset_Order exchangeGoodOrder)
    {
        return exchangeGoodOrderService.update(exchangeGoodOrder);
    }
}
