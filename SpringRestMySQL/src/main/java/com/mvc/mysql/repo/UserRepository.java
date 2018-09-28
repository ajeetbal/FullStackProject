package com.mvc.mysql.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mvc.mysql.entity.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
