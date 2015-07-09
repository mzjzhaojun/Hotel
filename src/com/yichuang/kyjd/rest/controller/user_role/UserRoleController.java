package com.yichuang.kyjd.rest.controller.user_role;

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
import com.yichuang.kyjd.rest.service.user_role.impl.User_roleServiceImpl;
import com.yichuang.kyjd.rest.entity.user_role.User_role;

/**
* @author zj    default  test
* 
* @version 1.1
*/


@Controller
@RequestMapping("/rest/userrole")
public class UserRoleController extends BaseController<User_role, Integer> {

  @Autowired
  private User_roleServiceImpl service;

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
			 User_role u = null;
			 if (json != null) {
			 	 u = (User_role) JSONObject.toBean(JSONObject.fromObject(json),User_role.class);
			 }
  		 List<User_role> list = service.selectUser_role(u);
  		 super.setSuccess(list);
  	}catch (Exception e) {
  		super.setError(e.getMessage());
      }
  	super.ResponseJson(response);
  }
}
