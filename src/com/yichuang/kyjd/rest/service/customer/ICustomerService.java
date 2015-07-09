package com.yichuang.kyjd.rest.service.customer;

import java.util.List;
import com.yichuang.kyjd.rest.entity.customer.Customer;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface ICustomerService {
  public List<Customer> selectCustomer(Customer t);
}