package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.Servicio;
import crisalis.blue.models.dto.ServicioDTO;
import crisalis.blue.repositories.ServicioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioService {
    private final ServicioRepository serviceRepository;

    public ServicioService(ServicioRepository serviceRepository)
    {
        this.serviceRepository = serviceRepository;
    }
    public ServicioDTO create(Servicio service)
    {
        return serviceRepository.save(service).servicieToDto();
    }
    public List<ServicioDTO> read()
    {
        return serviceRepository.findAll().stream().map(Servicio::servicieToDto).collect(Collectors.toList());
    }
    public ServicioDTO update(Servicio servicio)
    {
        if(checkServicios(servicio))
        {
            return serviceRepository.save(servicio).servicieToDto();
        }
        throw new EmptyElementException("Los datos de ExchangeGood estan vacios o el dato support_charge " +
                "no es mayor a cero ");
    }
    public boolean checkServicios(Servicio service)
    {

            if(service.getSupport_charge() > 0)
            {
                return true;
            }

       return false;
    }
    public ServicioDTO delete(Long id)
    {
        Optional<Servicio> aux = serviceRepository.findById(id);
        if(aux.isPresent())
        {
            serviceRepository.deleteById(id);
            return aux.get().servicieToDto();
        }
        throw new EmptyElementException("La entrada que se intento eliminar no existe ");
    }
}
