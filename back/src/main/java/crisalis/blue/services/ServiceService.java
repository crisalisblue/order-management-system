package crisalis.blue.services;

import crisalis.blue.exceptions.custom.EmptyElementException;
import crisalis.blue.models.Service;
import crisalis.blue.models.dto.ServiceDTO;
import crisalis.blue.repositories.ServiceRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository)
    {
        this.serviceRepository = serviceRepository;
    }
    public ServiceDTO create(Service service)
    {
        return serviceRepository.save(service).servicieToDto();
    }
    public List<ServiceDTO> read()
    {
        return serviceRepository.findAll().stream().map(Service::servicieToDto).collect(Collectors.toList());
    }
    public ServiceDTO update(Service servicio)
    {
        if(checkServicios(servicio))
        {
            return serviceRepository.save(servicio).servicieToDto();
        }
        throw new EmptyElementException("Los datos de ExchangeGood estan vacios o el dato support_charge " +
                "no es mayor a cero ");
    }
    public boolean checkServicios(Service service)
    {

        if(service.getSupportFree() > 0)
        {
            return true;
        }

        return false;
    }
    public ServiceDTO delete(Long id)
    {
        Optional<Service> aux = serviceRepository.findById(id);
        if(aux.isPresent())
        {
            serviceRepository.deleteById(id);
            return aux.get().servicieToDto();
        }
        throw new EmptyElementException("La entrada que se intento eliminar no existe ");
    }
}