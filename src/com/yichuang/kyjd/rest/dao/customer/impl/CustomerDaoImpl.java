package com.yichuang.kyjd.rest.dao.customer.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.yichuang.kyjd.rest.dao.customer.ICustomerDao;
import com.yichuang.kyjd.commnd.base.impl.BaseDaoImpl;
import com.yichuang.kyjd.rest.entity.customer.Customer;

/**
 * @author zj default
 * 
 * @version 1.1
 */

@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Customer, Integer> implements
		ICustomerDao {
	public List<Customer> getList(String statement, Customer t) {
		return super.selectList(statement, t);
	}
	
	
	public Object register(String statement, Customer t) {
		return super.insertObject(statement, t);
	}
	
	public Customer selectStatement(String statement,Customer t){
		return (Customer)super.selectOneObject(statement, t);
	}
	public Integer retrievePassword(String statement,Customer t){
		return (Integer)super.updateObject(statement, t);
	}
}