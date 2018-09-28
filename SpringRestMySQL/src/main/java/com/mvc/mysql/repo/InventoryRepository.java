package com.mvc.mysql.repo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.mysql.entity.InventoryEntity;

@Repository
public interface InventoryRepository extends CrudRepository<InventoryEntity, Long> {
	
}
