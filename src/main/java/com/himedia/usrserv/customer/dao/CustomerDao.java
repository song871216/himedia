package com.himedia.usrserv.customer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.himedia.usrserv.basedao.AbstractDao;
import com.himedia.usrserv.common.CommonHelper;
import com.himedia.usrserv.customer.model.Customer;

@Repository
public class CustomerDao extends AbstractDao<Customer> {

	/**
	 * 按照身份证查询用户信息
	 * @param identify
	 * @return
	 */
	public Customer findCustomerByIdentify(String identify) {
		Customer customer = new Customer();
		customer.setIdentify(identify);
		
		List<Customer> customers = super.findByEntity(customer);
		
		if( CommonHelper.isNullOrEmpty(customers) ) {
			return null;
		}
		return customers.iterator().next();
	}

	public void updateLastLogin(Long customerId) {
		// TODO Auto-generated method stub
		
	}

}
