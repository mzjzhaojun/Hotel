package com.yichuang.kyjd.rest.service.order_detail.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yichuang.kyjd.rest.dao.order_detail.impl.Order_detailDaoImpl;
import com.yichuang.kyjd.rest.service.order_detail.IOrder_detailService;
import com.yichuang.kyjd.commnd.base.impl.BaseServiceImpl;
import com.yichuang.kyjd.rest.entity.order_detail.Order_detail;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Service
public class Order_detailServiceImpl extends BaseServiceImpl<Order_detail, Integer> implements IOrder_detailService{
  @Autowired
  private Order_detailDaoImpl dao;

  public List<Order_detail> selectOrder_detail(Order_detail t){
       return dao.getList("selectOrder_detail", t);
  }
}