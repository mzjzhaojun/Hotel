package com.yichuang.kyjd.rest.service.role_module.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yichuang.kyjd.rest.dao.role_module.impl.Role_moduleDaoImpl;
import com.yichuang.kyjd.rest.service.role_module.IRole_moduleService;
import com.yichuang.kyjd.commnd.base.impl.BaseServiceImpl;
import com.yichuang.kyjd.rest.entity.role_module.Role_module;
import com.yichuang.kyjd.rest.entity.user_role.User_role;

/**
 * @author zj default
 * 
 * @version 1.1
 */

@Service
public class Role_moduleServiceImpl extends
		BaseServiceImpl<Role_module, Integer> implements IRole_moduleService {
	@Autowired
	private Role_moduleDaoImpl dao;

	public List<Object> getModuleCode(User_role t) {
		return dao.getModuleCode("selectModuleCode",t);
	}
	
	
	public List<Object> getListParentModuleCode(Role_module t){
		return dao.getListParentModuleCode("selectParentModuleCode", t);
	}
	/**
	 * 根据角色查询菜单
	 */
	public List<Object> getRoleMenuByRoleCode(Role_module t){
		return dao.getModuleCode("selectModuleCodeByRoleCode", t);
	}
	
	/**
	 * 根据角色code删除关联信息
	 */
	public void deleteByRoleCode(String  t){
		 dao.deleteByIdObject("deleteByRoleCode", t);
	}
	
	@Override
	public List<Role_module> selectRole_module(Role_module t) {
		// TODO Auto-generated method stub
		return null;
	}
}