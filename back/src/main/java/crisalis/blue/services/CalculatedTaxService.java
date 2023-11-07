package crisalis.blue.services;

import crisalis.blue.models.dto.CalculatedTaxDTO;
import crisalis.blue.repositories.CalculatedTaxRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatedTaxService {
    private CalculatedTaxRepository calculatedTaxRepository;

    public CalculatedTaxService(CalculatedTaxRepository calculatedTaxRepository)
    {
        this.calculatedTaxRepository = calculatedTaxRepository;
    }
    public CalculatedTaxDTO create(CalculatedTaxDTO calculatedTaxDTO)
    {
        return null;
    }
    public List<CalculatedTaxDTO> read()
    {
        return null;
        // calculatedTaxRepository.findAll().stream().map(CalculatedTax::toCalculateTaxDTO)
    }
}
