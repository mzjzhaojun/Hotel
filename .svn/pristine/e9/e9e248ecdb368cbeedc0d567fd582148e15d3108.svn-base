package com.yichuang.kyjd.rest.service.role.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yichuang.kyjd.commnd.base.impl.BaseServiceImpl;
import com.yichuang.kyjd.rest.dao.role.impl.RoleDaoImpl;
import com.yichuang.kyjd.rest.entity.role.Role;
import com.yichuang.kyjd.rest.service.role.IRoleService;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements IRoleService{
  @Autowired
  private RoleDaoImpl dao;

  public List<Role> selectRole(Role t){
       return dao.getList("selectRole", t);
  }
  
  //查询role角色是否存在
	public Role getRoleName(Role t) {
		return dao.getRole("getRoleName", t);
	}
	
	public Role save(Role t){
	  return dao.insert(t);
	}
	
	public List<Role> getRoleByRoleCode(Role role){
		return dao.getList("getRoleByRoleCode", role);
		
	}
	
	public List<Role> getList(Role role){
		return dao.getList("getList", role);
		
	}
}