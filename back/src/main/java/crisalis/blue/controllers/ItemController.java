package crisalis.blue.controllers;

import crisalis.blue.models.Item;
import crisalis.blue.models.dto.ItemDTO;
import crisalis.blue.services.ItemService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item")
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService exchangeGoodOrderService)
    {
        this.itemService = exchangeGoodOrderService;
    }
    @PostMapping(value = "create",produces= MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO create(@RequestBody ItemDTO itemDTO)
    {
        return itemService.create(itemDTO);
    }
    @GetMapping(value = "read",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> read()
    {
        return itemService.read();
    }
    @PutMapping(value="update",produces=MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO update(@RequestBody ItemDTO itemDTO)
    {
        return itemService.update(itemDTO);
    }
    @DeleteMapping(value="delete")
    public void delete(@RequestParam Long id)
    {
        itemService.delete(id);
    }
}
