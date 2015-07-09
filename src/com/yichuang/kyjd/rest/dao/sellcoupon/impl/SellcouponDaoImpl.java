package com.yichuang.kyjd.rest.dao.sellcoupon.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.yichuang.kyjd.rest.dao.sellcoupon.ISellcouponDao;
import com.yichuang.kyjd.commnd.base.impl.BaseDaoImpl;
import com.yichuang.kyjd.rest.entity.sellcoupon.Sellcoupon;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Repository
public class SellcouponDaoImpl extends BaseDaoImpl<Sellcoupon, Integer> implements ISellcouponDao{
public List<Sellcoupon> getList(String statement,Sellcoupon t){
  return super.selectList(statement, t);
  }
}