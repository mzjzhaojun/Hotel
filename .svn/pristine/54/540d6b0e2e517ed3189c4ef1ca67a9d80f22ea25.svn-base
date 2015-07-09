package com.yichuang.kyjd.rest.dao.order_info.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.yichuang.kyjd.rest.dao.order_info.IOrder_infoDao;
import com.yichuang.kyjd.commnd.base.impl.BaseDaoImpl;
import com.yichuang.kyjd.rest.entity.order_info.Order_info;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Repository
public class Order_infoDaoImpl extends BaseDaoImpl<Order_info, Integer> implements IOrder_infoDao{
public List<Order_info> getList(String statement,Order_info t){
  return super.selectList(statement, t);
  }
}