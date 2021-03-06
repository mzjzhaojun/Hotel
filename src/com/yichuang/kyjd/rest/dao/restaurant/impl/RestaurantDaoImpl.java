package com.yichuang.kyjd.rest.dao.restaurant.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.yichuang.kyjd.rest.dao.restaurant.IRestaurantDao;
import com.yichuang.kyjd.commnd.base.impl.BaseDaoImpl;
import com.yichuang.kyjd.rest.entity.restaurant.Restaurant;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Repository
public class RestaurantDaoImpl extends BaseDaoImpl<Restaurant, Integer> implements IRestaurantDao{
public List<Restaurant> getList(String statement,Restaurant t){
  return super.selectList(statement, t);
  }
}