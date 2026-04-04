package umc.pp.Workshop17.repository.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.pp.Workshop17.model.staff.Mechanic;

import java.util.UUID;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, UUID> {
    boolean existsByTaxID(String taxId);
}
