package umc.pp.Workshop17.repository.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.pp.Workshop17.model.service.EntryCheckList;
import umc.pp.Workshop17.model.vehicle.Vehicle;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface EntryCheckListRepository extends JpaRepository<EntryCheckList, Long> {
    Optional<EntryCheckList> findByVehicle(Vehicle vehicle);
}
