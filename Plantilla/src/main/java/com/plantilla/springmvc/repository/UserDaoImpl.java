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
//@Transactional
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public User findById(int id) {
		return getByKey(id);
	}

//	public User findByLoginUser(String email) {
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("LoginUser", email));
//		return (User) crit.uniqueResult();
//	}
//	@Autowired	
//	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public User findByEmail(String email) {

		List<User> users;// = new ArrayList<User>();
		users = getSession2().createQuery("from Users where Email=?").setParameter(0, email)
				.list();
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
	@Autowired
	private SessionFactory sessionFactory;	

	protected Session getSession2() {
		return sessionFactory.getCurrentSession();
	}
	
//	@SuppressWarnings("unchecked")
//	public List<User> findAllUser() {
//		Criteria criteria = createEntityCriteria();
//		return (List<User>) criteria.list();
//	}

}
