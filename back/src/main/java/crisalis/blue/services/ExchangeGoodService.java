package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.ExchangeGood;
import crisalis.blue.models.dto.ExchangeGoodDTO;
import crisalis.blue.repositories.ExchangeGoodRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class ExchangeGoodService {
    private final ExchangeGoodRepository itemsRespository;

    public ExchangeGoodService(ExchangeGoodRepository itemsRepository) {
        this.itemsRespository = itemsRepository;
    }

    public ExchangeGood create(ExchangeGood items) {
        if (checkItem(items)) {
            return itemsRespository.save(items);
        } else {
            throw new EmptyElementException("Error el nombre del producto o el monto base del mismo estan vacios ");
        }
    }

    private boolean checkItem(ExchangeGood exchangeGood) {
        boolean res = false;
        if(!exchangeGood.getName().isEmpty())
            res=true;
        if(exchangeGood.getMountBase() !=0.0)
            res=true;
        return res;
    }

    public List<ExchangeGoodDTO> read() {
        return this.itemsRespository.findAll().stream().map(ExchangeGood::toItemDTO).collect(Collectors.toList());
    }

    public ExchangeGoodDTO update(ExchangeGood exchangeGood) {
        Optional<ExchangeGood> aux = itemsRespository.findById(exchangeGood.getId());
        if (aux.isPresent()) {
            if (!exchangeGood.getName().isEmpty())
                aux.get().setName(exchangeGood.getName());
            if (exchangeGood.getMountBase() != 0.0)
                aux.get().setMountBase(exchangeGood.getMountBase());
            return this.itemsRespository.save(aux.get()).toItemDTO();
        }
        else throw new EmptyElementException("El elemento que se quiere actualizar no existe en la base de datos");
    }

    public ExchangeGoodDTO delete(Long id)
    {
        Optional<ExchangeGood> aux = itemsRespository.findById(id);
        if(aux.isPresent())
        {
            itemsRespository.deleteById(id);
            return aux.get().toItemDTO();
        }
        else throw new EmptyElementException("El id que se paso es invalido, no existe una entrada con ese elemento  ");
    }

}