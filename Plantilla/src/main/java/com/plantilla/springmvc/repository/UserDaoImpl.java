package com.plantilla.springmvc.repository;

//import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plantilla.springmvc.model.User;
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public User findById(int id) {
		return getByKey(id);
	}

//	public User findByEmail(String email) {
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("email", email));
//		return (User) crit.uniqueResult();
//	}
	
	@SuppressWarnings("unchecked")
	public User findByEmail(String email) {

		List<User> users;// = new ArrayList<User>();
		users = getSession().createQuery("from User where email=?").setParameter(0, email)
				.list();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
	
//	@SuppressWarnings("unchecked")
//	public List<User> findAllUser() {
//		Criteria criteria = createEntityCriteria();
//		return (List<User>) criteria.list();
//	}

}
