package crisalis.blue.controllers;

import crisalis.blue.models.dto.OrderDTO;
import crisalis.blue.services.OrderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDTO create(@RequestBody OrderDTO order) {
        return orderService.create(order);
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> read() {
        return orderService.read();
    }

    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDTO update(@RequestBody OrderDTO order) {
        return orderService.update(order);
    }

    @GetMapping(value = "read")
    public OrderDTO getOrderById(@RequestParam Long id) {
        return orderService.getOrderById(id);
    }

    @DeleteMapping(value = "delete")
    public void delete(@RequestParam Long id) {
        orderService.delete(id);
    }

    @PostMapping(value = "refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDTO refresh(@RequestBody OrderDTO order) {
        return orderService.refresh(order);
    }
}
