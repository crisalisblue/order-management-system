
package crisalis.blue.controllers;

import crisalis.blue.models.CalculatedTax;
import crisalis.blue.services.CalculatedTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import crisalis.blue.models.dto.CalculatedTaxDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("calculatedTax")
public class CalculatedTaxController {
    @Autowired
    private CalculatedTaxService calculatedTaxService;
    @PutMapping(value="create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public CalculatedTaxDTO create(@RequestBody CalculatedTaxDTO calculatedTaxDTO)
    {
        return this.calculatedTaxService.create(calculatedTaxDTO).calculatedTaxToDTO();
    }
    @GetMapping(value="read",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CalculatedTaxDTO> read()
    {
        return this.calculatedTaxService.read().stream().map(CalculatedTax::calculatedTaxToDTO).collect(Collectors.toList());
    }
    @PutMapping(value="update")
    public CalculatedTaxDTO update(@RequestBody CalculatedTaxDTO calculatedTaxDTO )
    {
        return this.calculatedTaxService.update(calculatedTaxDTO).calculatedTaxToDTO();
    }
    @DeleteMapping(value="delete")
    public void delete(@RequestParam Long id)
    {
        this.calculatedTaxService.delete(id);
    }
}