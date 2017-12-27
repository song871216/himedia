package com.himedia.usrserv.dao;

import java.io.Serializable;
import java.util.Collection;

public interface BaseDao<T> {
	 /**  
     * 保存实体  
     *   
     * @param entity  
     *            实体对象  
     * @return 实体主键  
     */  
	Serializable save(Object entity);  
  
    /**  
     *   
     * 删除实体  
     *   
     * @param entity  
     *            实体对象  
     *   
     */  
    void delete(Object entity);  
  
    /**  
     *   
     * 更新实体  
     *   
     * @param entity  
     *            实体对象  
     *   
     */  
    void update(Object entity);  
  
    /**  
     *   
     * 保存或更新实体, 实体没有主键时保存，否则更新  
     *   
     * @param entity  
     *            实体对象  
     *   
     */  
    void saveOrUpdate(Object entity);  
  
    /**  
     *   
     * 批量保存实体  
     *   
     * @param entities  
     *            实体集合  
     */  
    void saveAll(Collection<?> entities);  
  
    /**  
     *   
     * 批量删除实体  
     *   
     * @param entities  
     *            实体集合  
     *   
     */  
    void deleteAll(Collection<?> entities);  
  
    /**  
     *   
     * 批量更新实体  
     *   
     * @param entities  
     *            实体集合  
     *   
     */  
    void updateAll(Collection<?> entity);  
  
    /**  
     *   
     * 批量保存或更新实体, 实体没有主键时保存，否则更新  
     *   
     * @param entity  
     *            实体集合  
     *   
     */  
    void saveOrUpdateAll(Collection<?> entities);  
  
    /**  
     *   
     * 获取单个实体，根据实体类及实体的主键获取。  
     *   
     * @param entityClass  
     *            实体类  
     * @param id  
     *            实体主键  
     * @return 实体对象  
     */  
    T get(Class<T> entityClass, Serializable id);  
    
}
