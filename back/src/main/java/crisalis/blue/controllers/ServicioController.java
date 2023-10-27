package crisalis.blue.controllers;

import crisalis.blue.models.Servicio;
import crisalis.blue.models.dto.ServicioDTO;
import crisalis.blue.services.ServicioService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("servicio")
public class ServicioController {
    private ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }
    @PostMapping(value ="create",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServicioDTO create (@RequestBody Servicio servicio) {
        return  servicioService.create(servicio);
    }
    @GetMapping(value="read",produces =MediaType.APPLICATION_JSON_VALUE)
    public List<ServicioDTO> read()
    {
        return servicioService.read();
    }
    @PutMapping(value="update",produces=MediaType.APPLICATION_JSON_VALUE)
    public ServicioDTO update(@RequestBody Servicio servicio)
    {
        return servicioService.update(servicio);
    }
    @DeleteMapping(value="delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServicioDTO delete(@PathVariable  Long id)
    {
        return servicioService.delete(id);
    }

}
