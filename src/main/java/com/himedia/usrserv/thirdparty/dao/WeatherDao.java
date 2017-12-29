package com.himedia.usrserv.thirdparty.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.himedia.usrserv.basedao.AbstractDao;
import com.himedia.usrserv.thirdparty.domain.WeatherCityCode;

@Repository
public class WeatherDao extends AbstractDao<WeatherCityCode> {

	@SuppressWarnings("unchecked")
	public List<WeatherCityCode> matchByCityName(String cityName) {
		Criteria criteria = getSession().createCriteria(WeatherCityCode.class);
		
		criteria.add(Restrictions.like("cityName", cityName, MatchMode.ANYWHERE));
		return (List<WeatherCityCode>) criteria.list();
	}

	public String getCityNameByCityCode(String cityCode) {
		Criteria criteria = getSession().createCriteria(WeatherCityCode.class);
		criteria.add(Restrictions.eq("cityCode", cityCode));
		Object result = criteria.uniqueResult();
		
		if( result != null ) {
			return ((WeatherCityCode) result).getCityName();
		}
		return null;
	}
	
}
