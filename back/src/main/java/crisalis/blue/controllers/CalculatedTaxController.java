
package crisalis.blue.controllers;

import crisalis.blue.models.CalculatedTax;
import crisalis.blue.models.dto.CalculatedTaxDTO;
import crisalis.blue.services.CalculatedTaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("calculatedTax")
public class CalculatedTaxController {
    @Autowired
    private CalculatedTaxService calculatedTaxService;
    @PutMapping(value="create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public CalculatedTaxDTO create(@RequestBody  CalculatedTaxDTO calculatedTaxDTO)
    {
        return this.calculatedTaxService.create(calculatedTaxDTO);
    }
    @GetMapping(value="read",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CalculatedTaxDTO> read()
    {
        return this.calculatedTaxService.read();
    }
    @PutMapping(value="update")
    public CalculatedTaxDTO update(@RequestBody CalculatedTaxDTO calculatedTaxDTO )
    {
        return this.calculatedTaxService.update(calculatedTaxDTO);
    }
    @DeleteMapping(value="delete")
    public void delete(@RequestParam Long id)
    {
        this.calculatedTaxService.delete(id);
    }
}