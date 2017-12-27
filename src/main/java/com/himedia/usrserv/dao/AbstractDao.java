package com.himedia.usrserv.dao;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public abstract class AbstractDao<T> extends HibernateDaoSupport implements BaseDao<T> {

	@Autowired
	SessionFactory sessionFactory;
	
	@PostConstruct
	protected void init() {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public Serializable save(Object entity) {
		return getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(Object entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(Object entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public void saveOrUpdate(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public void saveAll(Collection<?> entities) {
		entities.forEach( entity -> getHibernateTemplate().save(entity) );
	}

	@Override
	public void deleteAll(Collection<?> entities) {
		entities.forEach( entity -> getHibernateTemplate().delete(entity) );
	}

	@Override
	public void updateAll(Collection<?> entities) {
		entities.forEach( entity -> getHibernateTemplate().update(entity) );
	}

	@Override
	public void saveOrUpdateAll(Collection<?> entities) {
		entities.forEach( entity -> getHibernateTemplate().saveOrUpdate(entity) );
	}

	@Override
	public T get(Class<T> entityClass, Serializable id) {
		return getHibernateTemplate().load(entityClass, id);
	}

}
