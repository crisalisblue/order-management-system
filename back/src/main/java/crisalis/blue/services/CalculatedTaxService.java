package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.exceptions.custom.ResourceNotFoundException;
import crisalis.blue.models.CalculatedTax;
import crisalis.blue.models.dto.CalculatedTaxDTO;
import crisalis.blue.models.dto.CustomerDTO;
import crisalis.blue.repositories.CalculatedTaxRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalculatedTaxService {
    private CalculatedTaxRepository calculatedTaxRepository;

    public CalculatedTaxService(CalculatedTaxRepository calculatedTaxRepository) {
        this.calculatedTaxRepository = calculatedTaxRepository;
    }

    public CalculatedTaxDTO create(CalculatedTaxDTO calculatedTaxDTO) {
        CalculatedTax calculatedTax = null;
        if (calculatedTaxDTO != null) {
            calculatedTax = new CalculatedTax();
            if (calculatedTaxDTO.getId() != null && calculatedTaxDTO.getId().intValue() != 0)
                calculatedTax.setId(calculatedTaxDTO.getId());
            if (calculatedTaxDTO.getIdTax() != null)
                calculatedTax.setIdTax(calculatedTaxDTO.getIdTax());
            if (calculatedTaxDTO.getIdOrder() != null)
                calculatedTax.setIdOrder(calculatedTaxDTO.getIdOrder());
            return calculatedTaxRepository.save(calculatedTax).calculatedTaxtoDTO();
        } else
            throw new EmptyElementException("No se entregaron datos");
    }

    public List<CalculatedTaxDTO> read() {
        return calculatedTaxRepository.findAll().stream().map(CalculatedTax::calculatedTaxtoDTO)
                .collect(Collectors.toList());
    }

    public CalculatedTaxDTO update(CalculatedTaxDTO calculatedTaxDTO) {
        if (calculatedTaxDTO != null) {
            CalculatedTax calculatedTax = new CalculatedTax();
            if (calculatedTaxDTO.getId() != null) {
                Optional<CalculatedTax> calculatedTaxOptional = calculatedTaxRepository
                        .findById(calculatedTaxDTO.getId());
                if (calculatedTaxOptional.isPresent()) {
                    calculatedTaxOptional.get().setIdTax(calculatedTax.getIdTax());
                    calculatedTaxOptional.get().setIdOrder(calculatedTaxDTO.getIdOrder());
                    calculatedTaxOptional.get().setTaxesAmount(calculatedTaxDTO.getTaxesAmount());
                    return calculatedTaxRepository.save(calculatedTaxOptional.get()).calculatedTaxtoDTO();
                }
            }
        }
        return null;
    }

    public void delete(Long id) {
        if (calculatedTaxRepository.existsById(id))
            calculatedTaxRepository.deleteById(id);
    }

    public CalculatedTaxDTO getCalculatedTaxById(Long id) {
        return this.calculatedTaxRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Calculated Tax not Found"))
                .calculatedTaxtoDTO();
    }
}
