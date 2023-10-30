package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.ExchangeGood;
import crisalis.blue.models.dto.ItemsDTO;
import crisalis.blue.repositories.ExchangeGoodRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class ExchangeGoodService {
    private ExchangeGoodRepository itemsRespository;

    public ExchangeGoodService(ExchangeGoodRepository itemsRepository)
    {
        this.itemsRespository = itemsRepository;
    }
    public ExchangeGood create(ExchangeGood items)
    {
        if(checkItem(items)) {
            return itemsRespository.save(items);
        }
        else
        {
            throw new EmptyElementException("Error el nombre del producto o el monto base del mismo estan vacios ");
        }
    }
    public List<ItemsDTO> read()
    {
         return this.itemsRespository.findAll().stream().map(ExchangeGood::toItemDTO).collect(Collectors.toList());
    }
    public ItemsDTO update(ExchangeGood exchangeGood)
    {
        if(checkItem(exchangeGood)) {
            Optional<ExchangeGood> aux =itemsRespository.findById(exchangeGood.getId());
            if(aux.isPresent()) {
                return this.itemsRespository.save(exchangeGood).toItemDTO();
            }
            else throw new EmptyElementException("El elemento que se quiere actualizar no existe en la base de datos");
        }
        else{
            throw new EmptyElementException("Los campos del bien de cambio que se quiso cargar estan vacios ");}
    }
    public ItemsDTO delete(Long id)
    {
        Optional<ExchangeGood> aux = itemsRespository.findById(id);
        if(aux.isPresent())
        {
            itemsRespository.deleteById(id);
            return aux.get().toItemDTO();
        }
        else throw new EmptyElementException("El id que se paso es invalido, no existe una entrada con ese elemento  ");
    }
    private boolean checkItem(ExchangeGood item)
    {
        if(!item.getName().isEmpty() && item.getMountBase() != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}