package org.seydaliev.repository;

import org.seydaliev.model.Computer;
import org.seydaliev.model.Device;
import org.seydaliev.model.Smartphone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SmartphoneRepository extends JpaRepository<Smartphone, Long> {
}
