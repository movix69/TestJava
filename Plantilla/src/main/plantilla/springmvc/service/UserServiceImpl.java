package main.plantilla.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.plantilla.springmvc.repository.UserDao;
//import com.plantilla.springmvc.repository.UserDaoImpl;
import main.plantilla.springmvc.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;	

	public User findById(int id) {
		return dao.findById(id);
	}

	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}

}
