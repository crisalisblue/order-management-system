package crisalis.blue.services;


import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.exceptions.custom.NotCreatedException;
import crisalis.blue.exceptions.custom.ResourceNotFoundException;
import crisalis.blue.models.Tax;
import crisalis.blue.models.dto.TaxDTO;
import crisalis.blue.repositories.CalculatedTaxRepository;
import crisalis.blue.repositories.TaxRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaxService {

    private final TaxRepository taxRepository;
    private final CalculatedTaxRepository calculatedTaxRepository;

    public TaxService(TaxRepository taxRepository,CalculatedTaxRepository calculatedTaxRepository){
        this.taxRepository = taxRepository;
        this.calculatedTaxRepository = calculatedTaxRepository;
    }

    public TaxDTO createTax(TaxDTO tax){
      if(tax != null) {
          Tax taxAux = tax.toTax();
          return  this.taxRepository.save(taxAux).toDTO();
      }
      else throw new EmptyElementException("TaxDTO vacio ");
    }
    public TaxDTO updateTax(TaxDTO updatedTax) throws Exception{

            Optional<Tax> taxOptional = taxRepository.findById(updatedTax.getId());
            if (taxOptional.isPresent()) {
                //Guardamos en tax, los datos del impuesto que esta en la base de datos.
                Tax tax = taxOptional.get();
                //En tax Asignamos los nuevos valores que reemplazaremos en la db
                tax.setName(updatedTax.getName());
                tax.setPercentage(updatedTax.getPercentage());
                tax.setBaseAmount(updatedTax.getBaseAmount());
                taxRepository.save(tax);

                return tax.toDTO();
            }

            throw new NotCreatedException("Error updating Tax");

    }

    public String deleteTax(Long id){

        if (taxRepository.existsById(id)){
            taxRepository.deleteById(id);
            return "Impuesto " + id +" Borrado exitosamente";
        }
        throw new ResourceNotFoundException("No existe un impuesto con id " + id + ".");
    }

    public List<TaxDTO> getAllTaxes(){
        try {
            return this
                    .taxRepository
                    .findAll()
                    .stream()
                    .map(Tax::toDTO)
                    .collect(Collectors.toList());
        } catch (Error e){
            throw new ResourceNotFoundException("Error al conseguir los Impuestos " +e.getMessage());
        }
    }

    public TaxDTO getTaxById(Long id) {
        return this.taxRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Impuesto no encontrado")
                ).toDTO();
    }
}
