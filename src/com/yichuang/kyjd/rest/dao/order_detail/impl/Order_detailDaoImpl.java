package com.yichuang.kyjd.rest.dao.order_detail.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.yichuang.kyjd.rest.dao.order_detail.IOrder_detailDao;
import com.yichuang.kyjd.commnd.base.impl.BaseDaoImpl;
import com.yichuang.kyjd.rest.entity.order_detail.Order_detail;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Repository
public class Order_detailDaoImpl extends BaseDaoImpl<Order_detail, Integer> implements IOrder_detailDao{
public List<Order_detail> getList(String statement,Order_detail t){
  return super.selectList(statement, t);
  }
}