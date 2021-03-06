package com.yichuang.kyjd.rest.controller.restaurant;

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
import com.yichuang.kyjd.rest.service.restaurant.impl.RestaurantServiceImpl;
import com.yichuang.kyjd.rest.entity.restaurant.Restaurant;

/**
* @author zj    default  test
* 
* @version 1.1
*/


@Controller
@RequestMapping("/rest/restaurant")
public class RestaurantController extends BaseController<Restaurant, Integer> {

  @Autowired
  private RestaurantServiceImpl service;

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
			 Restaurant u = null;
			 if (json != null) {
			 	 u = (Restaurant) JSONObject.toBean(JSONObject.fromObject(json),Restaurant.class);
			 }
  		 List<Restaurant> list = service.selectRestaurant(u);
  		 super.setSuccess(list);
  	}catch (Exception e) {
  		super.setError(e.getMessage());
      }
  	super.ResponseJson(response);
  }
}
