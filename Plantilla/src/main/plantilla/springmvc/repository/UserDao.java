package main.plantilla.springmvc.repository;

import main.plantilla.springmvc.model.User;

public interface UserDao {

	User findById(int id);

	User findByEmail(String email);

}
