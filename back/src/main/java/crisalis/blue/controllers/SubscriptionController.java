package crisalis.blue.controllers;

import crisalis.blue.models.dto.CustomerDTO;
import crisalis.blue.models.dto.SubscriptionDTO;
import crisalis.blue.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {this.subscriptionService = subscriptionService;}

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SubscriptionDTO createSubscription(@RequestBody SubscriptionDTO subscription) throws Exception {
        return this.subscriptionService.createSubscription(subscription);
    }

    @PutMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE)

    public SubscriptionDTO updateSubscription(@RequestBody SubscriptionDTO subscription) throws Exception {
        return subscriptionService.updateSubscription(subscription);
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SubscriptionDTO> getAllSubscriptions() {
        return this.subscriptionService.getAllSubscriptions();
    }


    @GetMapping(value = "read", produces = MediaType.APPLICATION_JSON_VALUE)
    public SubscriptionDTO getSubscriptionById(@RequestParam Long id) {
        return this.subscriptionService.getSubscriptionById(id);
    }

    @DeleteMapping(value = "delete")
    public String deleteSubscription(@RequestParam Long id ) {
        return subscriptionService.deleteSubscription(id);
    }
}
