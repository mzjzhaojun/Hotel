package com.yichuang.kyjd.rest.service.restaurant.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yichuang.kyjd.rest.dao.restaurant.impl.RestaurantDaoImpl;
import com.yichuang.kyjd.rest.service.restaurant.IRestaurantService;
import com.yichuang.kyjd.commnd.base.impl.BaseServiceImpl;
import com.yichuang.kyjd.rest.entity.restaurant.Restaurant;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Service
public class RestaurantServiceImpl extends BaseServiceImpl<Restaurant, Integer> implements IRestaurantService{
  @Autowired
  private RestaurantDaoImpl dao;

  public List<Restaurant> selectRestaurant(Restaurant t){
       return dao.getList("selectRestaurant", t);
  }
}