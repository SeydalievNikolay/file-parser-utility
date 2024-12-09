package org.seydaliev.repository;

import org.seydaliev.model.Computer;
import org.seydaliev.model.Device;
import org.seydaliev.model.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {
}

