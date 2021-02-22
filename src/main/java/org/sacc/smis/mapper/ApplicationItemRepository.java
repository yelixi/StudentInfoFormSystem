package org.sacc.smis.mapper;

import org.sacc.smis.entity.ApplicationItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationItemRepository extends JpaRepository<ApplicationItem, Integer> {
}
