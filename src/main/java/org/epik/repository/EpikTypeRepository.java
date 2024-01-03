package org.epik.repository;

import org.epik.model.repo.EpikType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpikTypeRepository extends JpaRepository<EpikType, Integer> {
}
