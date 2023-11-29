package crisalis.blue.services;

import crisalis.blue.exceptions.custom.IntegrityViolationException;
import crisalis.blue.exceptions.custom.NotCreatedException;
import crisalis.blue.exceptions.custom.ResourceNotFoundException;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final CustomerRepository customerRepository;
    private final AssetRepository assetRepository;


    public SubscriptionDTO createSubscription(SubscriptionDTO sub) {
        try {
            Subscription newSubscription = new Subscription();

            newSubscription.setStatus(sub.getStatus());
            //Asigno el cliente en base al id que me llego en "customer"
            Optional <Customer> customer = customerRepository.findById(sub.getCustomer());
            newSubscription.setCustomer(customer.get());
            //Asigno el cliente en base al id que me llego en "asset"
            Optional<Asset> asset = assetRepository.findById(sub.getAsset());
            newSubscription.setAsset(asset.get());

            subscriptionRepository.save(newSubscription);

            return newSubscription.toDTO();

        } catch (Error e) {
            throw new NotCreatedException("Error al asociar la subscripcion");
        }
    }

    public SubscriptionDTO updateSubscription(SubscriptionDTO subscription) {

        Optional<Subscription> subscriptionOptional = subscriptionRepository.findById(subscription.getId());

        if (!subscriptionOptional.isPresent()){
            throw new ResourceNotFoundException("No se encontro la Subscripcion.");
        }
        //Instancio la subscripcion que se encuentra en la db
        Subscription mySubscription = subscriptionOptional.get();

        mySubscription.setStatus(subscription.getStatus());
        subscriptionRepository.save(mySubscription);

        return mySubscription.toDTO();
    }

    public List<SubscriptionDTO> getAllSubscriptions() {

        return this.subscriptionRepository
                .findAll()
                .stream()
                .map(Subscription::toDTO)
                .collect(Collectors.toList());
    }

    public SubscriptionDTO getSubscriptionById(Long id) {

        return this.subscriptionRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Subscription not Found"))
                .toDTO();
    }


    public String deleteSubscription(Long id) {
        try {
            subscriptionRepository.deleteById(id);
            return "Subscripcion " + id + " Borrada exitosamente";

        } catch (DataIntegrityViolationException e) {
            if (!customerRepository.existsById(id)) {
                throw new ResourceNotFoundException("No existe una subscripcion con id " + id + ".");
            }
            throw new IntegrityViolationException("Error al borrar");

        }
    }

    public Subscription getSubscriptionByAssetIdAndCustomerId(Long id_asset, Long id_customer){
        return subscriptionRepository.findByAssetIdAndCustomerId(id_asset, id_customer).orElse(null);
    }
}
