package umc.pp.Workshop17.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.pp.Workshop17.model.customer.Customer;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    boolean existByTaxId(String taxID);
    Optional<Customer> findByTaxId(String taxID);
}
