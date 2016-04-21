package com.plantilla.springmvc.service;

import com.plantilla.springmvc.model.User;

public interface UserService {

	User findById(int id);

	User findByEmail(String email);

}