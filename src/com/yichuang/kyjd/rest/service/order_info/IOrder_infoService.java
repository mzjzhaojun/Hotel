package com.yichuang.kyjd.rest.service.order_info;

import java.util.List;
import com.yichuang.kyjd.rest.entity.order_info.Order_info;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface IOrder_infoService {
  public List<Order_info> selectOrder_info(Order_info t);
}