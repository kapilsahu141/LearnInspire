package com.learnSphere.services;


import com.learnSphere.entities.Users;

public interface UsersService {
	
	String addUser(Users user);

	Users findUserByEmail(String email);
	
	boolean checkEmail(String email);
	
	String saveUser(Users user);

	
}
