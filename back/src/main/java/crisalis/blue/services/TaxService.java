package crisalis.blue.services;


import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.exceptions.custom.NotCreatedException;
import crisalis.blue.exceptions.custom.ResourceNotFoundException;
import crisalis.blue.models.Asset;
import crisalis.blue.models.Order;
import crisalis.blue.models.Tax;
import crisalis.blue.models.dto.TaxDTO;
import crisalis.blue.repositories.AssetRepository;
import crisalis.blue.repositories.OrderRepository;
import crisalis.blue.repositories.TaxRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaxService {

    private final TaxRepository taxRepository;
    private final AssetRepository assetRepository;
    private final OrderRepository orderRepository;

    public TaxService(TaxRepository taxRepository, AssetRepository assetRepository,OrderRepository orderRepository){
        this.taxRepository = taxRepository;
        this.assetRepository = assetRepository;
        this.orderRepository = orderRepository;
    }

    public TaxDTO createTax(TaxDTO tax) throws Exception{
        try {
            Tax taxAux = new Tax(null,tax.getName(),tax.getBaseAmount(),tax.getPercentage(),
                    buscarOrders(tax.getOrdersList()),buscarAsset(tax.getAssetList()));
           return this.taxRepository.save(taxAux).toDTO();
        }catch (DataIntegrityViolationException | HibernateJdbcException e){
            throw new NotCreatedException(e.getMessage());
        }
    }
    private List<Asset> buscarAsset(List<Long>listAsset)
    {
        if(listAsset != null) {
            if (!listAsset.isEmpty()) {
                List<Asset> listR = new ArrayList<Asset>();
                Long actual = null;

                for (int j = 0; j < listAsset.size(); j++) {
                    actual = listAsset.get(j);
                    if (assetRepository.existsById(actual)) {
                        listR.add(assetRepository.findById(actual).get());
                    }
                }
                return listR;
            }

        }
        return null;
    }
    private List<Order> buscarOrders(List<Long> listOrders)
    {
        if(listOrders != null) {
            if (!listOrders.isEmpty()) {
                List<Order> listR = new ArrayList<>();
                Long actual = null;
                for (int j = 0; j < listOrders.size(); j++) {
                    actual = listOrders.get(j);
                    if (orderRepository.existsById(actual)) {
                        listR.add(orderRepository.findById(actual).get());
                    }
                }
                return listR;
            }
        }
            return null;
    }
    public TaxDTO updateTax(TaxDTO updatedTax) throws Exception{

            Optional<Tax> taxOptional = taxRepository.findById(updatedTax.getId());
            if (taxOptional.isPresent()) {
                //Guardamos en tax, los datos del impuesto que esta en la base de datos.
                Tax tax = taxOptional.get();
                //En tax Asignamos los nuevos valores que reemplazaremos en la db
                tax.setName(updatedTax.getName());
                tax.setPercentage(updatedTax.getPercentage());
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
