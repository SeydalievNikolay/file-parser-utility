package org.seydaliev.repository;

import org.seydaliev.model.Device;
import org.seydaliev.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}
