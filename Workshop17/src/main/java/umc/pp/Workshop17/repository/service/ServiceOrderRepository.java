package umc.pp.Workshop17.repository.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.pp.Workshop17.model.service.ServiceOrder;
import umc.pp.Workshop17.model.staff.Mechanic;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
    boolean existsByMechanic(Mechanic mechanic);
}
