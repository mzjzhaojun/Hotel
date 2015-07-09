package com.yichuang.kyjd.rest.controller.order_detail;

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
import com.yichuang.kyjd.rest.service.order_detail.impl.Order_detailServiceImpl;
import com.yichuang.kyjd.rest.entity.order_detail.Order_detail;

/**
* @author zj    default  test
* 
* @version 1.1
*/


@Controller
@RequestMapping("/rest/orderdetail")
public class OrderDetailController extends BaseController<Order_detail, Integer> {

  @Autowired
  private Order_detailServiceImpl service;

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
			 Order_detail u = null;
			 if (json != null) {
			 	 u = (Order_detail) JSONObject.toBean(JSONObject.fromObject(json),Order_detail.class);
			 }
  		 List<Order_detail> list = service.selectOrder_detail(u);
  		 super.setSuccess(list);
  	}catch (Exception e) {
  		super.setError(e.getMessage());
      }
  	super.ResponseJson(response);
  }
}
