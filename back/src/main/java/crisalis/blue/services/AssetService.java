package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.Asset;
import crisalis.blue.models.Product;
import crisalis.blue.models.Tax;
import crisalis.blue.models.dto.AssetDTO;
import crisalis.blue.models.dto.TaxDTO;
import crisalis.blue.repositories.AssetRepository;
import crisalis.blue.repositories.TaxRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public AssetDTO create(AssetDTO assetDTO) {
        Product product = null;
        crisalis.blue.models.Service service =null;
        Asset asset =null;
        if (checkAsset(assetDTO)) {
            if(assetDTO.getType()!=null) {
                if (assetDTO.getType().equals("Product"))
                    asset = new Product();

                else
                    asset = new crisalis.blue.models.Service();
            }
            if (assetDTO.getName() != null && !assetDTO.getName().isEmpty())
                asset.setName(assetDTO.getName());
            if (assetDTO.getBaseAmount() != null && !BigDecimal.ZERO.equals(assetDTO.getBaseAmount()))
                asset.setBaseAmount(assetDTO.getBaseAmount());
            if (assetDTO.getTaxDTOList() != null)
                asset.setTaxList(assetDTO.getTaxDTOList().stream().map(TaxDTO::toTax).collect(Collectors.toList()));
            if(asset instanceof crisalis.blue.models.Service)
            {
                if(assetDTO.getSupportFee() == null  )
                {
                    ((crisalis.blue.models.Service)asset).setSupportFee(BigDecimal.ZERO);
                }
                else
                {
                    ((crisalis.blue.models.Service) asset).setSupportFee(assetDTO.getSupportFee());
                    service = (crisalis.blue.models.Service) asset;
                }
                return assetRepository.save(service).toAssetDTO();
            } else {
                product = (Product) asset;
                return assetRepository.save(product).toAssetDTO();
            }
        } else {
            throw new EmptyElementException("Error el nombre del producto o el monto base del mismo estan vacios ");
        }
    }

    private List<Tax> buscarTax(List<TaxDTO> listTax) {
        List<Tax> listR = new ArrayList<>();
        Optional<Tax> optionalTax = null;
        if (listTax != null) {
            if (!listTax.isEmpty()) {
                for (int j = 0; j < listTax.size(); j++) {
                    listR.add(taxRepository.findById(listTax.get(j).getId()).get());
                }
            }
        }
        return listR;
    }

    private boolean checkAsset(AssetDTO assetDTO) {
        boolean res = false;
        if (!assetDTO.getName().isEmpty())
            res = true;
        if (assetDTO.getBaseAmount().intValue() != 0)
            res = true;
        if (assetDTO.getTaxDTOList() != null)
            res = true;
        return res;
    }

    public List<AssetDTO> read() {
        return this.assetRepository.findAll().stream().map(Asset::toAssetDTO).collect(Collectors.toList());
    }

    public List<AssetDTO> readType(String type) {
        return this.assetRepository.findAll().stream().map(Asset::toAssetDTO)
                .filter(assetDTO -> assetDTO.getType().equals(type)).collect(Collectors.toList());
    }

    public AssetDTO update(AssetDTO assetDTO) {
        Optional<Asset> aux = assetRepository.findById(assetDTO.getId());
        Product product = null;
        crisalis.blue.models.Service service = null;
        if (aux.isPresent()) {
            if (!assetDTO.getName().isEmpty())
                aux.get().setName(assetDTO.getName());
            if (assetDTO.getBaseAmount().intValue() != 0)
                aux.get().setBaseAmount(assetDTO.getBaseAmount());
            if (assetDTO.getTaxDTOList() != null)
                buscarTax(assetDTO.getTaxDTOList());
              if(assetDTO.getSupportFee() != null && assetDTO.getSupportFee().intValue() != 0) {
                service = (crisalis.blue.models.Service) aux.get();
                service.setSupportFee(assetDTO.getSupportFee());
                return this.assetRepository.save(service).toAssetDTO();
            } else {
                product = (Product) aux.get();
                return this.assetRepository.save(product).toAssetDTO();
            }

        } else
            throw new EmptyElementException("El elemento que se quiere actualizar no existe en la base de datos");
    }

    public void delete(Long id) {
        Optional<Asset> aux = assetRepository.findById(id);
        if (aux.isPresent()) {
            assetRepository.deleteById(id);
        } else
            throw new EmptyElementException("El id que se paso es invalido, no existe una entrada con ese elemento  ");
    }

}