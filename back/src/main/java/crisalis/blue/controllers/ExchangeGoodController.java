package crisalis.blue.controllers;

import crisalis.blue.models.ExchangeGood;
import crisalis.blue.models.dto.ItemsDTO;
import crisalis.blue.services.ExchangeGoodService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
public class ExchangeGoodController {
    private final ExchangeGoodService itemsService;

    public ExchangeGoodController(ExchangeGoodService itemsService)
    {
        this.itemsService = itemsService;
    }
    @PostMapping(value="create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExchangeGood post(@RequestBody ExchangeGood productoCambio )
    {
        return itemsService.create(productoCambio);
    }
    @GetMapping(value="read", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemsDTO> read() {return itemsService.read();}
    @PutMapping(value="update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemsDTO update(@RequestBody ExchangeGood exchangeGood)
    {
        return itemsService.update(exchangeGood);
    }
    @DeleteMapping(value="delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemsDTO delete(@PathVariable  Long id )
    {
        return itemsService.delete(id);
    }
}