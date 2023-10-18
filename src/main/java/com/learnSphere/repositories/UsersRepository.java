package com.learnSphere.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learnSphere.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{
	
	Users findByEmail(String email);
	
	boolean existsByEmail(String email);

	@Query("select u from Users u where u.email= :email")
	public Users getUserByUserName(@Param("email") String email);

}
