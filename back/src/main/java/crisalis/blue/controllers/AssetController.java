package crisalis.blue.controllers;

import crisalis.blue.models.Asset;
import crisalis.blue.models.dto.AssestDTO;
import crisalis.blue.services.AssetService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("exchangeGood")
public class AssetController {
    private final AssetService itemsService;

    public AssetController(AssetService itemsService)
    {
        this.itemsService = itemsService;
    }
    @PostMapping(value="create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Asset post(@RequestBody Asset productoCambio )
    {
        return itemsService.create(productoCambio);
    }
    @GetMapping(value="read", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AssestDTO> read() {return itemsService.read();}
    @PutMapping(value="update", produces = MediaType.APPLICATION_JSON_VALUE)
    public AssestDTO update(@RequestBody Asset exchangeGood)
    {
        return itemsService.update(exchangeGood);
    }
    @DeleteMapping(value="delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public AssestDTO delete(@PathVariable  Long id )
    {
        return itemsService.delete(id);
    }
}

