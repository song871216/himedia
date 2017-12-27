package com.himedia.usrserv.customer.service;

import java.io.Serializable;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himedia.usrserv.common.ErrorCode;
import com.himedia.usrserv.common.HiMediaException;
import com.himedia.usrserv.customer.dao.CustomerDao;
import com.himedia.usrserv.customer.model.Customer;
import com.himedia.usrserv.customer.pojo.LoginById;
import com.himedia.usrserv.customer.pojo.LoginResult;
import com.himedia.usrserv.enums.CustomerType;

@Service
public class CustomerService {
	
	static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	CustomerDao customerDao;
	
	@Transactional
	public LoginResult processLoginByIdentify(LoginById loginById) throws HiMediaException {
		
		Customer customer = customerDao.findCustomerByIdentify(loginById.getIdentify());
		if( customer == null ) {
			//自动进行注册
			return regAndLogin(loginById);
		}else {
			if( StringUtils.equals(customer.getLoginPsw(), loginById.getLoginPsw()) ) {
				logger.info("valid user, login success.");
				Date lastLogin = customer.getLastLogin() == null ? new Date() : customer.getLastLogin();
				
				customerDao.updateLastLogin(customer.getCustomerId());
				
				CustomerType customerType = CustomerType.parseof( customer.getRoleType() );
				return new LoginResult(customer.getNickName(), lastLogin, customerType.type(), customerType.desc() );
			}else {
				logger.error("user has changed default password, cannot login by id.");
				throw new HiMediaException(ErrorCode.ID_LOGIN_ERROR);
			}
		}
	}

	private LoginResult regAndLogin(LoginById loginById) {
		logger.info("start to reg new user: {}", loginById);
		Customer customer = new Customer();
		BeanUtils.copyProperties(loginById, customer);
		
		String lastName = loginById.getSex() == 0 ? "先生" : "女士";
		String nickName = loginById.getRealName().substring(0, 1) + lastName;
		customer.setLoginName(loginById.getIdentify());
		customer.setNickName(nickName);
		customer.setCreateDate(new Date());
		customer.setUpdateDate(new Date());
		customer.setLastLogin(new Date());
		customer.setRoleType(CustomerType.USER.type());
		
		logger.info("start to save customer: {}", customer);
		Serializable id = customerDao.save(customer);
		logger.info("user reg success, id: {}", id);
		return new LoginResult(customer.getNickName(), customer.getLastLogin(), CustomerType.USER.type(), CustomerType.USER.desc() );
	}

}
