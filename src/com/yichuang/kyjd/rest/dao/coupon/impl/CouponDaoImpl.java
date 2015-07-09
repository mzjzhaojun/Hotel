package com.yichuang.kyjd.rest.dao.coupon.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.yichuang.kyjd.rest.dao.coupon.ICouponDao;
import com.yichuang.kyjd.commnd.base.impl.BaseDaoImpl;
import com.yichuang.kyjd.rest.entity.coupon.Coupon;

/**
 * @author zj default
 * 
 * @version 1.1
 */

@Repository
public class CouponDaoImpl extends BaseDaoImpl<Coupon, Integer> implements
		ICouponDao {
	public List<Coupon> getList(String statement, Coupon t) {
		return super.selectList(statement, t);
	}
}