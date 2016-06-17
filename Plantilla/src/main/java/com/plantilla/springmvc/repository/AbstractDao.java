package com.plantilla.springmvc.repository;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Criteria;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;		

	protected Session getSession() {		
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {		
		getSession().persist(entity);
		//getSession().save(entity);
		getSession().flush();		
	}

	public void delete(T entity) {
		getSession().delete(entity);
		getSession().flush();
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<T>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findBy(ArrayList<String[]> fieldNameFilterName) {			
		Criteria crit = createEntityCriteria();
		for(int i=0;i<fieldNameFilterName.size();i++){
			   String[] myString= new String[2]; 
			   myString=fieldNameFilterName.get(i);
			   crit.add(Restrictions.eq(myString[0], myString[1]));
		}
		return (List<T>)crit.list();//crit.uniqueResult();
	}		
}
