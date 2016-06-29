package main.plantilla.springmvc.service;

import main.plantilla.springmvc.model.User;

public interface UserService {

	User findById(int id);

	User findByEmail(String email);

}