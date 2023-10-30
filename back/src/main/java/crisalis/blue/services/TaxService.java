package crisalis.blue.services;


import crisalis.blue.exceptions.custom.NotCreatedException;
import crisalis.blue.models.Tax;
import crisalis.blue.models.dto.TaxDTO;
import crisalis.blue.repositories.TaxRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

    private final TaxRepository taxRepository;

    public TaxService(TaxRepository taxRepository){
        this.taxRepository = taxRepository;
    }

    public TaxDTO createTax(Tax tax) throws Exception{
        try {
            this.taxRepository.save(tax);
            return tax.ToDTO();
        }catch (DataIntegrityViolationException | HibernateJdbcException e){
            throw new NotCreatedException(e.getMessage());
        }
    }
}
