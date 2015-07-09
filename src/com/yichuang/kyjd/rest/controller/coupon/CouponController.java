package com.yichuang.kyjd.rest.controller.coupon;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.yichuang.kyjd.commnd.base.impl.BaseController;
import com.yichuang.kyjd.commnd.web.StaticSources;
import net.sf.json.JSONObject;
import com.yichuang.kyjd.rest.service.coupon.impl.CouponServiceImpl;
import com.yichuang.kyjd.rest.entity.coupon.Coupon;

/**
* @author zj    default  test
* 
* @version 1.1
*/


@Controller
@RequestMapping("/rest/coupon")
public class CouponController extends BaseController<Coupon, Integer> {

  @Autowired
  private CouponServiceImpl service;

  @Autowired
  public void setBaseService() {
		setBaseService(service);
	}


  /**
  * custom 
  * 
  * @version 1.1
  */
  @RequestMapping(value = "/param1", method = RequestMethod.GET)
  public void customSelect(HttpServletRequest request,HttpServletResponse response) {
  	try {
  		 String json = request.getParameter(StaticSources.JSONVO);
			 Coupon u = null;
			 if (json != null) {
			 	 u = (Coupon) JSONObject.toBean(JSONObject.fromObject(json),Coupon.class);
			 }
  		 List<Coupon> list = service.selectCoupon(u);
  		 super.setSuccess(list);
  	}catch (Exception e) {
  		super.setError(e.getMessage());
      }
  	super.ResponseJson(response);
  }
  
  /**
   * custom 
   * 
   * @version 1.1
   */
   @RequestMapping(value = "/sendcustomer", method = RequestMethod.GET)
   public void sendcustomer(HttpServletRequest request,HttpServletResponse response) {
   	try {
   		 String json = request.getParameter(StaticSources.JSONVO);
 			 Coupon u = null;
 			 if (json != null) {
 			 	 u = (Coupon) JSONObject.toBean(JSONObject.fromObject(json),Coupon.class);
 			 }
   		 List<Coupon> list = service.selectCoupon(u);
   		 super.setSuccess(list);
   	}catch (Exception e) {
   		super.setError(e.getMessage());
       }
   	super.ResponseJson(response);
   }
  
}
