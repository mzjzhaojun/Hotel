package com.yichuang.kyjd.rest.dao.restaurant;

import java.util.List;
import com.yichuang.kyjd.rest.entity.restaurant.Restaurant;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface IRestaurantDao {
  public List<Restaurant> getList(String statement,Restaurant t);
}