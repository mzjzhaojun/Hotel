package com.yichuang.kyjd.rest.service.coupon.impl;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yichuang.kyjd.rest.dao.coupon.impl.CouponDaoImpl;
import com.yichuang.kyjd.rest.service.coupon.ICouponService;
import com.yichuang.kyjd.commnd.base.impl.BaseServiceImpl;
import com.yichuang.kyjd.commnd.system.util.bean.BeanMapUtil;
import com.yichuang.kyjd.rest.entity.coupon.Coupon;

/**
 * @author zj default
 * 
 * @version 1.1
 */

@Service
public class CouponServiceImpl extends BaseServiceImpl<Coupon, Integer>
		implements ICouponService {
	@Autowired
	private CouponDaoImpl dao;

	public List<Coupon> selectCoupon(Coupon t) {
		return dao.getList("selectCoupon", t);
	}
	
	public void deleteCoupon(){
		dao.deleteByIdObject("dropTable","1");
	}
	
	
	public Object selectCouponSurplus(String rowid){
		return dao.selectOneObject("selectCouponSurplus", rowid);
	}
	
	
	public Object getByName(String name){
		Coupon t = new Coupon();
		t.setName(name);
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanMapUtil.beanToMap(t);
		} catch (Exception e) {
		}
		return dao.selectOneObject("getByName", paramMap);
	}
	
	public Object updateCoupon(Coupon t){
		return dao.selectOneObject("updateCoupon", t);
	}
}