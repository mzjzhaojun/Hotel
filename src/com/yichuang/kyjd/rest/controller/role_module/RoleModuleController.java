package com.yichuang.kyjd.rest.controller.role_module;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yichuang.kyjd.commnd.base.impl.BaseController;
import com.yichuang.kyjd.commnd.web.StaticSources;
import com.yichuang.kyjd.rest.entity.role_module.Menu;
import com.yichuang.kyjd.rest.entity.role_module.Role_module;
import com.yichuang.kyjd.rest.entity.user.User;
import com.yichuang.kyjd.rest.entity.user_role.User_role;
import com.yichuang.kyjd.rest.service.role_module.impl.Role_moduleServiceImpl;
import com.yichuang.kyjd.rest.service.user_role.impl.User_roleServiceImpl;

/**
 * @author zj default test
 * 
 * @version 1.1
 */

@Controller
@RequestMapping("/rest/rolemodule")
public class RoleModuleController extends BaseController<Role_module, Integer> {

	@Autowired
	private Role_moduleServiceImpl service;
	
	@Autowired
	private User_roleServiceImpl urservice;

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
	public void customSelect(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String json = request.getParameter(StaticSources.JSONVO);
			Role_module u = null;
			if (json != null) {
				u = (Role_module) JSONObject.toBean(
						JSONObject.fromObject(json), Role_module.class);
			}
			List<Role_module> list = service.selectRole_module(u);
			super.setSuccess(list);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}
	
	

	/**
	 * custom
	 * 
	 * @version 1.1
	 */
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public void getMenu(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("token");
			Role_module rm = new Role_module();
			User_role ur = new User_role();
			ur.setUser_code(user.getUser_code());
			//查询role_code
			ur = urservice.getUserRole(ur);
			rm.setRole_code(ur.getRole_code());
			//查询module_code
			List<Object> map = service.getModuleCode(ur);
			for(int i=0;i<map.size();i++){
				rm = new Role_module();
				Menu menu =  (Menu)map.get(i);
				rm.setModule_code(menu.getModule_code());
				rm.setRole_code(ur.getRole_code());
				//查询子模块
				List<Object> map1 = service.getListParentModuleCode(rm);
				menu.setChildren(map1);
			}
			super.setSuccess(map);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}
	
	/**
	 * 根据角色获取菜单
	 * 
	 * @version 1.1
	 */
	@RequestMapping(value = "/rolemenu", method = RequestMethod.GET)
	public void getRoleMenu(HttpServletRequest request, HttpServletResponse response) {
		try {
			String s= request.getParameter("role_code");
			Role_module rm = new Role_module();
			rm.setRole_code(s);
			//查询module_code
			List<Object> map = service.getRoleMenuByRoleCode(rm);
			
			super.setSuccess(map);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}
}
