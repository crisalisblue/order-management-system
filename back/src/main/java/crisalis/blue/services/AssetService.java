package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.Asset;
import crisalis.blue.models.Product;
import crisalis.blue.models.Servicie;
import crisalis.blue.models.Tax;
import crisalis.blue.models.dto.AssetDTO;
import crisalis.blue.models.dto.TaxDTO;
import crisalis.blue.repositories.AssetRepository;
import crisalis.blue.repositories.TaxRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class AssetService {
    private final AssetRepository assetRepository;
    private final TaxRepository taxRepository;

    public AssetService(AssetRepository itemsRepository, TaxRepository taxRepository) {
        this.assetRepository = itemsRepository;
        this.taxRepository = taxRepository;
    }

    public AssetDTO create(AssetDTO assetDTO) throws RuntimeException {
        Product product = null;
        Servicie servicie =null;
        checkAsset(assetDTO);
        if(assetDTO.getType().equals("Product"))
        {
            product = convertirUnAssetProduct(assetDTO);
            product.setTaxList(updateListTax(assetDTO.getTaxDTOList()));
            return assetRepository.save(product).toAssetDTO();
        }
        else
        {
            servicie = convertirUnAssetService(assetDTO);
            servicie.setTaxList(updateListTax(assetDTO.getTaxDTOList()));
            servicie.setSupportFee(assetDTO.getSupportFee());
            return assetRepository.save(servicie).toAssetDTO();
        }


    }

    private void checkAsset(AssetDTO assetDTO) throws RuntimeException {
        boolean res = false;
        if(assetDTO.getName().isEmpty())
            throw new EmptyElementException("El id es nulo");
        if(assetDTO.getBaseAmount().intValue() ==0)
            throw new EmptyElementException("Base amount es nulo");
        if(assetDTO.getTaxDTOList() ==null)
             throw new EmptyElementException("La lista impuesto es nula ");
        if(assetDTO.getType() == null)
            throw new EmptyElementException("No se envio el tipo de activo");
    }

    public List<AssetDTO> read() {
        return this.assetRepository.findAll().stream().map(Asset::toAssetDTO).collect(Collectors.toList());
    }
    public List<AssetDTO> readType(String type) {
        return this.assetRepository.findAll().stream().map(Asset::toAssetDTO).
                filter(assetDTO -> assetDTO.getType().equals(type)).collect(Collectors.toList());
    }

    public AssetDTO update(AssetDTO assetDTO) throws RuntimeException {
            checkAsset(assetDTO);
            Asset asset = null;
            Optional<Asset> optionalAsset = assetRepository.findById(assetDTO.getId());
            if(optionalAsset.isPresent()) {
                asset = setDatos(assetDTO);
                asset.setTaxList(updateListTax(assetDTO.getTaxDTOList()));
                return assetRepository.save(asset).toAssetDTO();
            }
            else
                throw new EmptyElementException("El id ingresasdo es invalido o no esta en la base de datos ");
    }
private Asset setDatos(AssetDTO assetDTO) throws RuntimeException
{
    Asset asset = null;
    if (assetDTO != null) {
        if (assetDTO.getType().equals("Service")) {
            Servicie servicie = new Servicie(assetDTO);
            asset = servicie;
        }
        else {
            Product product = new Product(assetDTO);
            asset = product;
        }
        return asset;
    }
    else
        throw new EmptyElementException("El elemento que se quiere actualizar no existe en la base de datos");
    }
private Product convertirUnAssetProduct(AssetDTO asset)
{
    Product product = new Product();
    product.setId(asset.getId());
    product.setName(asset.getName());
    product.setBaseAmount(asset.getBaseAmount());
    return product;
}
private Servicie convertirUnAssetService(AssetDTO asset)
{
    Servicie servicie = new Servicie();
    servicie.setName(asset.getName());
    servicie.setId(asset.getId());
    servicie.setBaseAmount(asset.getBaseAmount());
    return servicie;
}
private List<Tax> updateListTax(List<TaxDTO> list)
{
    if(list != null && !list.isEmpty()) {
        List<Tax> listTax = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Tax tax = obtenerTax(list.get(i));
            listTax.add(tax);
        }
        return listTax;
    }
    return new ArrayList<>();
    //else throw new EmptyElementException("La lista de impuesto es nula ");
}
private void updateTax(Tax tax,TaxDTO taxDTO)
{
    tax.setName(taxDTO.getName());
    tax.setBaseAmount(taxDTO.getBaseAmount());
    tax.setPercentage(taxDTO.getPercentage());
}
private Tax obtenerTax(TaxDTO taxDTO )
{
    if(taxDTO.getId() != null) {
        Tax tax = taxRepository.findById(taxDTO.getId()).get();
        updateTax(tax,taxDTO);
        return tax;
    }
    else
        return taxRepository.save(new Tax(taxDTO));
}
    public void delete(Long id)
    {
        Optional<Asset> aux = assetRepository.findById(id);
        if(aux.isPresent())
        {
            assetRepository.deleteById(id);
        }
        else throw new EmptyElementException("El id que se paso es invalido, no existe una entrada con ese elemento  ");
    }
    public AssetDTO getAssetById(Long id)
    {
    if(id !=null && id != 0)
        return assetRepository.findById(id).get().toAssetDTO();
    else
        throw new EmptyElementException("Id invalido o es cero o no llego ningun valor");
    }
}