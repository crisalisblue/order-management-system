package crisalis.blue.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import crisalis.blue.models.Items;
import crisalis.blue.models.dto.ItemDTO;
import crisalis.blue.services.ItemsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class ItemController {
    private final ItemsService itemService;

    public ItemController (ItemsService itemsService)
    {
        this.itemService = itemsService;
    }
    @PostMapping(value="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO post(@RequestBody Items items )
    {
        return null;
    }

}