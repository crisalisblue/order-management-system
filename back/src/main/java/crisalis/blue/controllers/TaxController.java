package crisalis.blue.controllers;


import crisalis.blue.models.Tax;
import crisalis.blue.models.dto.TaxDTO;
import crisalis.blue.services.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tax")
public class TaxController {
    private final TaxService taxService;

    @Autowired
    public TaxController(TaxService taxService) {this.taxService = taxService;}

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaxDTO createTax(@RequestBody Tax tax) throws Exception {
        return this.taxService.createTax(tax);
    }

    @PutMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaxDTO updateTax(@RequestBody Tax tax) throws Exception{
        return this.taxService.updateTax(tax);
    }

    @GetMapping(value = "list")
    public List<TaxDTO> getAllTaxes(){
        return this.taxService.getAllTaxes();
    }

    @GetMapping(value = "")
    public TaxDTO getTaxById(@RequestParam int id) {
        return this.taxService.getTaxById(id);
    }

    @DeleteMapping(value = "")
    public String deleteTax(@RequestParam int id){
        return this.taxService.deleteTax(id);
    }
}