package com.yichuang.kyjd.rest.service.order_info.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yichuang.kyjd.rest.dao.order_detail.impl.Order_detailDaoImpl;
import com.yichuang.kyjd.rest.dao.order_info.impl.Order_infoDaoImpl;
import com.yichuang.kyjd.rest.service.order_info.IOrder_infoService;
import com.yichuang.kyjd.commnd.base.impl.BaseServiceImpl;
import com.yichuang.kyjd.commnd.system.util.SubNumUtil;
import com.yichuang.kyjd.rest.entity.order_info.Order;
import com.yichuang.kyjd.rest.entity.order_info.Order_info;

/**
 * @author zj default
 * 
 * @version 1.1
 */

@Service
public class Order_infoServiceImpl extends BaseServiceImpl<Order_info, Integer>
		implements IOrder_infoService {
	@Autowired
	private Order_infoDaoImpl dao;

	@Autowired
	private Order_detailDaoImpl daodetai;

	public List<Order_info> selectOrder_info(Order_info t) {
		return dao.getList("selectOrder_info", t);
	}

	public Object insertOrder_info(Order t) {
		Order obj = (Order) dao.insertObject("insertorderinfo", t);
		if (obj.getRowid() > 0) {
			t.setOrderrowid(obj.getRowid() + "");
			t.setSubnum(SubNumUtil.getSubNumCode());
			daodetai.insertObject("insertorderdetail", t);
		}
		return t;
	}

	public void updateOrder(Order t) {
		dao.updateObject("updateOrder", t);

		daodetai.updateObject("updateOrderDetail", t);
	}

	public List<Object> selectOrderRoom(Order t) {

		return dao.selectListObject("selectOrderRoom", t);
	}

	public Object selectOrder(Order u) {

		return dao.selectOneObject("selectOrder", u);
	}

	public Object selectOrderInfo(Order u) {

		return dao.selectOneObject("selectOrderInfo", u);
	}

	public Object deleteid(String id) {
		daodetai.deleteByIdObject("deleteid", id);
		return dao.deleteByIdObject("deleteid", id);
	}

	public void updateOrderPay(Order t) {
		dao.updateObject("updateOrderPay", t);
	}

	public Object updateOrderDetail(Order u) {

		return dao.updateObject("updateOrderDetail", u);
	}

}