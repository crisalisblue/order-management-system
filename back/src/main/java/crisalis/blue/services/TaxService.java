package crisalis.blue.services;


import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.exceptions.custom.NotCreatedException;
import crisalis.blue.exceptions.custom.ResourceNotFoundException;
import crisalis.blue.models.Tax;
import crisalis.blue.models.dto.TaxDTO;
import crisalis.blue.repositories.TaxRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaxService {

    private final TaxRepository taxRepository;

    public TaxService(TaxRepository taxRepository){
        this.taxRepository = taxRepository;
    }

    public TaxDTO createTax(Tax tax) throws Exception{
        try {
            this.taxRepository.save(tax);
            return tax.toDTO();
        }catch (DataIntegrityViolationException | HibernateJdbcException e){
            throw new NotCreatedException(e.getMessage());
        }
    }

    public TaxDTO updateTax(Tax updatedTax) throws Exception{

            Optional<Tax> taxOptional = taxRepository.findById(updatedTax.getId().intValue());
            if (taxOptional.isPresent()){
                //Guardamos en tax, los datos del impuesto que esta en la base de datos.
                Tax tax = taxOptional.get();

                //En tax Asignamos los nuevos valores que reemplazaremos en la db
                tax.setName(updatedTax.getName());
                tax.setPercentage(updatedTax.getPercentage());
                tax.setFixedAmount(updatedTax.getFixedAmount());

                taxRepository.save(tax);

                return tax.toDTO();
            }

            throw new NotCreatedException("Error updating Tax");

    }

    public String deleteTax(int id){

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

    public TaxDTO getTaxById(int id) {
        return this.taxRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Impuesto no encontrado")
                ).toDTO();
    }
}
