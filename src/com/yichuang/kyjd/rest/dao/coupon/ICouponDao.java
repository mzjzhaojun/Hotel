package com.yichuang.kyjd.rest.dao.coupon;

import java.util.List;
import com.yichuang.kyjd.rest.entity.coupon.Coupon;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface ICouponDao {
  public List<Coupon> getList(String statement,Coupon t);
}