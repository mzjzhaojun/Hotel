package com.yichuang.kyjd.rest.controller.role;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yichuang.kyjd.commnd.base.impl.BaseController;
import com.yichuang.kyjd.commnd.web.StaticSources;
import com.yichuang.kyjd.rest.entity.role.Role;
import com.yichuang.kyjd.rest.entity.role_module.Role_module;
import com.yichuang.kyjd.rest.service.role.impl.RoleServiceImpl;
import com.yichuang.kyjd.rest.service.role_module.impl.Role_moduleServiceImpl;

/**
* @author zj    default  test
* 
* @version 1.1
*/


@Controller
@RequestMapping("/rest/role")
public class RoleController extends BaseController<Role, Integer> {

  @Autowired
  private RoleServiceImpl service;
  @Autowired
  private Role_moduleServiceImpl roleModelservice;

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
			 Role u = null;
			 if (json != null) {
			 	 u = (Role) JSONObject.toBean(JSONObject.fromObject(json),Role.class);
			 }
  		 List<Role> list = service.selectRole(u);
  		 super.setSuccess(list);
  	}catch (Exception e) {
  		super.setError(e.getMessage());
      }
  	super.ResponseJson(response);
  }
  	/**
	 * custom
	 * /rest/role/checkrolename   
	 * @version 1.1
	 */
	@RequestMapping(value = "/checkrolename", method = RequestMethod.GET)
	public void checkUserName(HttpServletRequest request, HttpServletResponse response) {
		try {
			String json = request.getParameter(StaticSources.JSONVO);
			Role u = null;
			if (json != null) {
				u = (Role) JSONObject.toBean(JSONObject.fromObject(json),
						Role.class);
			}
			super.setSuccess(service.getRoleName(u));
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}
	/**
	 * custom
	 * /rest/role/save  
	 * @version 1.1
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response) {
		try {
			Role role = null;
			Role_module role_module=null;
			String json = request.getParameter(StaticSources.JSONVO);
			if (json != null) {
				role = (Role) JSONObject.toBean(JSONObject.fromObject(json),
						Role.class);
			}
			if(role!=null){
				if(role.getModule_codes()!=null){
					String[] mc=role.getModule_codes().split(",");
					for (int i = 0; i < mc.length; i++) {
						if(role.getModule_codes()!=null&&!role.getModule_codes().equals("")&&mc[i]!=null&&!mc[i].equals("")){
							role_module=new Role_module();
							role_module.setRole_code(role.getRole_code());
							role_module.setModule_code(mc[i]);
							roleModelservice.insert(role_module);
						}
//						super.setSuccess(roleModelservice.insert(role_module));
					}
				}
			}
			
			super.setSuccess(service.insert(role));
			
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		
		super.ResponseJson(response);
		
	}
	/**
	 * 修改角色信息，包含角色权限关联表
	 * /rest/role/update 
	 * @version 1.1
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			Role role = null;
			Role_module role_module=null;
			String json = request.getParameter(StaticSources.JSONVO);
			if (json != null) {
				role = (Role) JSONObject.toBean(JSONObject.fromObject(json),
						Role.class);
			}
			if(role!=null){
				if(role.getModule_codes()!=null&&role.getModule_codes().length()>0){
					String[] mc=role.getModule_codes().split(",");
					//根据角色code删除关联模块信息
					roleModelservice.deleteByRoleCode(role.getRole_code());
					for (int i = 0; i < mc.length; i++) {
						if(role.getModule_codes()!=null&&!role.getModule_codes().equals("")&&mc[i]!=null&&!mc[i].equals("")){
							role_module=new Role_module();
							role_module.setRole_code(role.getRole_code());
							role_module.setModule_code(mc[i]);
							roleModelservice.insert(role_module);
						}
					}
				}
			}
			
			super.setSuccess(service.update(role));
			
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		
		super.ResponseJson(response);
		
	}
}
