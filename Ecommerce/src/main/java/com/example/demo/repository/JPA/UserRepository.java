package com.example.demo.repository.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
	public User getUserByMobileNumber(String mobileNumber);
	
	public User getUserByEmail(String email);

}
