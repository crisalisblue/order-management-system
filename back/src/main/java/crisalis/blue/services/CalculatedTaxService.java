package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.CalculatedTax;
import crisalis.blue.models.dto.CalculatedTaxDTO;
import crisalis.blue.repositories.CalculatedTaxRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalculatedTaxService {
    private CalculatedTaxRepository calculatedTaxRepository;

    public CalculatedTaxService(CalculatedTaxRepository calculatedTaxRepository)
    {
        this.calculatedTaxRepository = calculatedTaxRepository;
    }
    public CalculatedTax create(CalculatedTaxDTO calculatedTaxDTO)
    {
        CalculatedTax calculatedTax = null;
        if(calculatedTaxDTO != null)
        {
            calculatedTax = new CalculatedTax();
            if(calculatedTaxDTO.getCalculatedTaxID() != null && calculatedTaxDTO.getCalculatedTaxID().intValue() != 0)
                calculatedTax.setId(calculatedTaxDTO.getCalculatedTaxID());
            if(calculatedTaxDTO.getTaxID() != null)
                calculatedTax.setTax(calculatedTax.getTax());
            return calculatedTaxRepository.save(calculatedTax);
        }
        else
            throw new EmptyElementException("No se entregaron datos");
    }
    public List<CalculatedTax> read()
    {
        return calculatedTaxRepository.findAll().
                stream().collect(Collectors.toList());
    }
    public CalculatedTax update(CalculatedTaxDTO calculatedTaxDTO)
    {
        if(calculatedTaxDTO != null)
        {
            CalculatedTax calculatedTax = new CalculatedTax();
            if(calculatedTaxDTO.getCalculatedTaxID() != null)
            {
                Optional<CalculatedTax> calculatedTaxOptional =
                        calculatedTaxRepository.findById(calculatedTaxDTO.getCalculatedTaxID());
                if(calculatedTaxOptional.isPresent())
                {
                    calculatedTaxOptional.get().setTax(calculatedTax.getTax());
                    calculatedTaxOptional.get().setTaxesAmount(calculatedTaxDTO.getTaxesAmount());
                    return calculatedTaxRepository.save(calculatedTaxOptional.get());
                }
            }
        }
        return null;
    }

    public void delete(Long id)
    {
        if(calculatedTaxRepository.existsById(id))
            calculatedTaxRepository.deleteById(id);
    }
}

