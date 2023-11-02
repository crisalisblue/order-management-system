package crisalis.blue.controllers;

import crisalis.blue.models.Service;
import crisalis.blue.models.dto.ServiceDTO;
import crisalis.blue.services.ServiceService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("service")
public class ServiceController {
    private ServiceService servicioService;

    public ServiceController(ServiceService servicioService) {
        this.servicioService = servicioService;
    }
    @PostMapping(value ="create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceDTO create (@RequestBody Service servicio) {
        return  servicioService.create(servicio);
    }
    @GetMapping(value="read",produces =MediaType.APPLICATION_JSON_VALUE)
    public List<ServiceDTO> read()
    {
        return servicioService.read();
    }
    @PutMapping(value="update",produces=MediaType.APPLICATION_JSON_VALUE)
    public ServiceDTO update(@RequestBody Service servicio)
    {
        return servicioService.update(servicio);
    }
    @DeleteMapping(value="delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceDTO delete(@PathVariable  Long id)
    {
        return servicioService.delete(id);
    }

}