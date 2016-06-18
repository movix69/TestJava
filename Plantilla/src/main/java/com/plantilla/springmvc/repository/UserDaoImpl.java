package com.plantilla.springmvc.repository;

import java.util.ArrayList;
import java.util.List;
//import org.hibernate.Criteria;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plantilla.springmvc.model.User;
//import com.plantilla.springmvc.model.Roles;
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public User findById(int id) {
		return getByKey(id);
	}
	
	public User findByEmail(String data) {
		List<User> users;
		ArrayList<String[]> outerArr = new ArrayList<String[]>();
		String[] myDataFilter= {"email",data};
		outerArr.add(myDataFilter);
		users=findBy(outerArr);		
//		User buser=users.get(0);
//		List<Roles> broles=buser.getRoles();		
//		Roles rol=new Roles();		
//		rol.setRoleID(2);
//		broles.add(rol);
//		buser.setRoles(broles);
//		persist(buser);
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
	
//	@SuppressWarnings("unchecked")
//	public User findByEmail(String email) {
//
//		List<User> users;// = new ArrayList<User>();
//		users = getSession().createQuery("from User where email=?").setParameter(0, email)
//				.list();
//		if (users.size() > 0) {
//			return users.get(0);
//		} else {
//			return null;
//		}
//
//	}
}
