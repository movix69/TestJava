package com.plantilla.springmvc.repository;

import com.plantilla.springmvc.model.User;

public interface UserDao {

	User findById(int id);

	User findByEmail(String email);

}
