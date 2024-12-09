package org.seydaliev.repository;

import org.seydaliev.model.Computer;
import org.seydaliev.model.Device;
import org.seydaliev.model.VacuumCleaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacuumCleanerRepository extends JpaRepository<VacuumCleaner, Long> {
}
