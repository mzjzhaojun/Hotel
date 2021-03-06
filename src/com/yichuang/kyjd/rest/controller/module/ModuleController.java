package com.yichuang.kyjd.rest.controller.module;

import java.util.ArrayList;
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
import com.yichuang.kyjd.rest.entity.module.Module;
import com.yichuang.kyjd.rest.entity.role_module.Role_module;
import com.yichuang.kyjd.rest.service.module.impl.ModuleServiceImpl;
import com.yichuang.kyjd.rest.service.role_module.impl.Role_moduleServiceImpl;

/**
* @author zj    default  test
* 
* @version 1.1
*/


@Controller
@RequestMapping("/rest/module")
public class ModuleController extends BaseController<Module, Integer> {

  @Autowired
  private ModuleServiceImpl service;
  @Autowired
  private Role_moduleServiceImpl rmservice;

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
			 Module u = null;
			 if (json != null) {
			 	 u = (Module) JSONObject.toBean(JSONObject.fromObject(json),Module.class);
			 }
  		 List<Module> list = service.selectModule(u);
  		 super.setSuccess(list);
  	}catch (Exception e) {
  		super.setError(e.getMessage());
      }
  	super.ResponseJson(response);
  }
  /**
	 * 获取所有菜单并封装为树结构
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/menuall", method = RequestMethod.GET)
	public void getMenuAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			//查询module_code
			List<Module> listModule = service.selectModuleAll(new Module());//获取所有模块信息
			String s= request.getParameter("role_code");
			Role_module rm = new Role_module();
			rm.setRole_code(s);
			//查询module_code
			List<Object> rmList = rmservice.getRoleMenuByRoleCode(rm);//获取该角色所持有的菜单
			for (int i = 0; i < rmList.size(); i++) {
				Module m=(Module)rmList.get(i);
				for (int j = 0; j < listModule.size(); j++) {
					Module md=listModule.get(j);
					if(m.getModule_code().equals(md.getModule_code())){
						md.setIschecked("1");
					}
				}
			}
//			List<Module> listFirstModule=firstMenuList(listModule);
//			for (Module firstModule : listFirstModule) {
//				List<Module> secondList = secondLevelList(firstModule.getModule_code(),listModule);
////				firstModule.setListChildModule(secondList);
//			}
			super.setSuccess(listModule);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}
	
	/**
	 * 封装一级菜单
	 * @param listMenu
	 */
	public List<Module> firstMenuList(List<Module> listMenu){
		List<Module> firstList = new ArrayList<Module>();
		for (Module module : listMenu) {
			if(module.getLevel().intValue()==1){
				firstList.add(module);
			}
		}
		return firstList;
	}
	/**
	 * 封装二级菜单
	 * @param listMenu
	 */
	public  List<Module> secondLevelList(String firstModuleCode,List<Module> listModule){
		List<Module> secondList = new ArrayList<Module>();
		for (Module module : listModule) {
			if (module.getModule_code().startsWith(firstModuleCode)
					&&module.getLevel().intValue()==2 ) {
				secondList.add(module);
			}	
		}
		return secondList;
		
	}
	
}
