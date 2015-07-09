package com.yichuang.kyjd.rest.dao.order_info;

import java.util.List;
import com.yichuang.kyjd.rest.entity.order_info.Order_info;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface IOrder_infoDao {
  public List<Order_info> getList(String statement,Order_info t);
}