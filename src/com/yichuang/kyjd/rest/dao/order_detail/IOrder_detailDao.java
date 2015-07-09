package com.yichuang.kyjd.rest.dao.order_detail;

import java.util.List;
import com.yichuang.kyjd.rest.entity.order_detail.Order_detail;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface IOrder_detailDao {
  public List<Order_detail> getList(String statement,Order_detail t);
}