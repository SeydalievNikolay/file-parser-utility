package org.seydaliev.repository;

import org.seydaliev.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByNameIgnoreCaseContaining(String name);
}

