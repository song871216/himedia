package com.himedia.usrserv.basedao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

@Transactional
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
	
	@Override
	public List<T> findByEntity(T instance) {
		return getHibernateTemplate().findByExample(instance);
	}
	
	@Override
	public T findById(Serializable id) {
		return getHibernateTemplate().load(getActuallType(), id);
	}
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	protected Pager toPager(Criteria criteria, int pageNo, int pageSize) {
		Object countObj = criteria.setProjection(Projections.rowCount()).uniqueResult();
		
		if( countObj == null ) {
			return Pager.emptyPager();
		}
		
		long rowCount = ((Number) countObj).longValue();
		
		criteria.setProjection(null);
		criteria.setFirstResult( ( pageNo - 1 ) * pageSize )
		.setMaxResults(pageSize);
		
		return new Pager(pageSize, pageNo, rowCount, criteria.list());
	}
	
	@SuppressWarnings("unchecked")
	protected Class<T> getActuallType(){
		return (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected Criteria createCriteria() {
		return getSession().createCriteria(getActuallType());
	}
}
