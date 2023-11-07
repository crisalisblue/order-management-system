package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.Asset;
import crisalis.blue.models.Item;
import crisalis.blue.models.Tax;
import crisalis.blue.models.dto.AssetDTO;
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

    public AssetService(AssetRepository itemsRepository,TaxRepository taxRepository) {
        this.assetRepository = itemsRepository;
        this.taxRepository = taxRepository;
    }

    public AssetDTO create(AssetDTO assetDTO) {
        if (checkItem(assetDTO)) {
            Asset asset = new Asset();
            if(!assetDTO.getName().isEmpty())
                asset.setName(assetDTO.getName());
            if(assetDTO.getBaseAmount() !=null && assetDTO.getBaseAmount().intValue() != 0)
                asset.setBaseAmount(assetDTO.getBaseAmount());
            if(assetDTO.getTaxDTOList()!=null)
                asset.setTaxList(buscarTax(assetDTO.getTaxDTOList()));
            return assetRepository.save(asset).toAssetDTO();
        } else {
            throw new EmptyElementException("Error el nombre del producto o el monto base del mismo estan vacios ");
        }
    }
    private List<Tax> buscarTax(List<Long>listTax)
    {
        List<Tax> listR = new ArrayList<>();
        Long actual = null;
        if(listTax !=null)
        {
            if(!listTax.isEmpty())
            {
               for(int j=0; j<listTax.size(); j++)
               {
                   actual = listTax.get(j);
                   if(taxRepository.existsById(actual))
                   {
                       listR.add(taxRepository.findById(actual).get());
                   }
               }
            }
        }
        return listR;
    }
    private boolean checkItem(AssetDTO assetDTO) {
        boolean res = false;
        if(!assetDTO.getName().isEmpty())
            res=true;
        if(assetDTO.getBaseAmount().intValue() !=0)
            res=true;
        if(assetDTO.getTaxDTOList() !=null)
             res=true;
        return res;
    }

    public List<AssetDTO> read() {
        return this.assetRepository.findAll().stream().map(Asset::toAssetDTO).collect(Collectors.toList());
    }

    public AssetDTO update(AssetDTO assetDTO) {
        Optional<Asset> aux = assetRepository.findById(assetDTO.getId());
        if (aux.isPresent()) {
            if (!assetDTO.getName().isEmpty())
                aux.get().setName(assetDTO.getName());
            if (assetDTO.getBaseAmount().intValue() != 0)
                aux.get().setBaseAmount(assetDTO.getBaseAmount());
            return this.assetRepository.save(aux.get()).toAssetDTO();
        }
        else throw new EmptyElementException("El elemento que se quiere actualizar no existe en la base de datos");
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

}