package com.plantilla.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.plantilla.springmvc.repository.UserDao;
import com.plantilla.springmvc.repository.UserDaoImpl;
import com.plantilla.springmvc.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

//	@Autowired
//	private UserDao dao;
	
	private UserDaoImpl dao=new UserDaoImpl();

	public User findById(int id) {
		return dao.findById(id);
	}

	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}

}
