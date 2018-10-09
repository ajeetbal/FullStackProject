package com.mvc.mysql.repo;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mvc.mysql.entity.DistributorEntity;

@Repository
public interface DistributorRepository extends CrudRepository<DistributorEntity, Long> {
	
	@Query(value="SELECT * FROM distributor WHERE name=?1 AND password=?2",nativeQuery=true)
	List<DistributorEntity> findByNameAndPassword(String name,String password);
	
}
