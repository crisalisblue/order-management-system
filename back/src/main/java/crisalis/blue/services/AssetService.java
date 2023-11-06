package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.Asset;
import crisalis.blue.models.dto.AssestDTO;
import crisalis.blue.repositories.AssetRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class AssetService {
    private final AssetRepository itemsRespository;

    public AssetService(AssetRepository itemsRepository) {
        this.itemsRespository = itemsRepository;
    }

    public Asset create(Asset items) {
        if (checkItem(items)) {
            return itemsRespository.save(items);
        } else {
            throw new EmptyElementException("Error el nombre del producto o el monto base del mismo estan vacios ");
        }
    }

    private boolean checkItem(Asset exchangeGood) {
        boolean res = false;
        if(!exchangeGood.getName().isEmpty())
            res=true;
        if(exchangeGood.getMountBase() !=0.0)
            res=true;
        if(!exchangeGood.getTaxList().isEmpty())
             res=true;
        return true;
    }

    public List<AssestDTO> read() {
        return this.itemsRespository.findAll().stream().map(Asset::toItemDTO).collect(Collectors.toList());
    }

    public AssestDTO update(Asset exchangeGood) {
        Optional<Asset> aux = itemsRespository.findById(exchangeGood.getId());
        if (aux.isPresent()) {
            if (!exchangeGood.getName().isEmpty())
                aux.get().setName(exchangeGood.getName());
            if (exchangeGood.getMountBase() != 0.0)
                aux.get().setMountBase(exchangeGood.getMountBase());
            return this.itemsRespository.save(aux.get()).toItemDTO();
        }
        else throw new EmptyElementException("El elemento que se quiere actualizar no existe en la base de datos");
    }

    public AssestDTO delete(Long id)
    {
        Optional<Asset> aux = itemsRespository.findById(id);
        if(aux.isPresent())
        {
            itemsRespository.deleteById(id);
            return aux.get().toItemDTO();
        }
        else throw new EmptyElementException("El id que se paso es invalido, no existe una entrada con ese elemento  ");
    }

}