package crisalis.blue.services;

import crisalis.blue.exceptions.custom.NotCreatedException;
import crisalis.blue.models.Asset;
import crisalis.blue.models.Customer;
import crisalis.blue.models.Person;
import crisalis.blue.models.Subscription;
import crisalis.blue.models.dto.CustomerDTO;
import crisalis.blue.models.dto.SubscriptionDTO;
import crisalis.blue.repositories.AssetRepository;
import crisalis.blue.repositories.CustomerRepository;
import crisalis.blue.repositories.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final CustomerRepository customerRepository;

    private final AssetRepository assetRepository;

    /*public SubscriptionDTO createSubscription(SubscriptionDTO subscription) {
        try {
            //Subscription newSubscription = new Subscription(subscription);
            Subscription newSubscription = new Subscription();
            newSubscription.setStatus(Boolean.TRUE);
            newSubscription.setCustomer(new Person());
            newSubscription.setAsset();
            this.subscriptionRepository.save(newSubscription);
            //return newSubscription.toDTO();
        }catch (Error e){
            throw new NotCreatedException("Error al asociar la subscripcion");
        }
    }*/

    public SubscriptionDTO createSubscription(SubscriptionDTO sub) {
        try {
            //Subscription newSubscription = new Subscription(subscription);
            Subscription newSubscription = new Subscription();

            newSubscription.setStatus(sub.getStatus());
            Optional <Customer> customer = customerRepository.findById(sub.getCustomer());
            //Customer customeraux = customer.get();
            //newSubscription.setCustomer(customeraux);
            newSubscription.setCustomer(customer.get());
            Optional<Asset> asset = assetRepository.findById(sub.getAsset());
            //Asset assetaux = asset.get();
            //newSubscription.setAsset(assetaux);
            newSubscription.setAsset(asset.get());

            subscriptionRepository.save(newSubscription);

            return newSubscription.toDTO();
            /*return SubscriptionDTO.builder()
                    .id(newSubscription.getId())
                    .status(newSubscription.getStatus())
                    .customer(newSubscription.getCustomer().getId())
                    .asset(newSubscription.getAsset().getId())
                    .build();*/
        } catch (Error e) {
            throw new NotCreatedException("Error al asociar la subscripcion");
        }
    }

    public SubscriptionDTO updateSubscription(SubscriptionDTO subscription) {
        return null;
    }

    public List<SubscriptionDTO> getAllSubscriptions() {
        return null;
    }

    public CustomerDTO getSubscriptionById(Long id) {
        return null;
    }

    public String deleteSubscription(Long id) {
        return null;
    }
}
