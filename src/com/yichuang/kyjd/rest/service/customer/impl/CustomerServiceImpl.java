package com.yichuang.kyjd.rest.service.customer.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yichuang.kyjd.rest.dao.customer.impl.CustomerDaoImpl;
import com.yichuang.kyjd.rest.service.customer.ICustomerService;
import com.yichuang.kyjd.commnd.base.impl.BaseServiceImpl;
import com.yichuang.kyjd.rest.entity.customer.Customer;

/**
 * @author zj default
 * 
 * @version 1.1
 */

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Integer>
		implements ICustomerService {
	@Autowired
	private CustomerDaoImpl dao;

	public List<Customer> selectCustomer(Customer t) {
		return dao.getList("selectCustomer", t);
	}

	public Object register(Customer t) {
		return dao.register("register", t);
	}
	
	public Customer loginCustomer(Customer t){
		return dao.selectStatement("loginCustomer",t);
	}
	
	public Customer selectCustomerSalt(Customer t){
		return dao.selectStatement("selectCustomerSalt",t);
	}
	
	public Customer checkCustomer(Customer t){
		return dao.selectStatement("checkCustomer",t);
	}
	public Integer retrievePassword(Customer t){
	      return dao.retrievePassword("retrievePassword", t);
	}
}