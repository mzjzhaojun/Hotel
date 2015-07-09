package com.yichuang.kyjd.rest.dao.role_module.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yichuang.kyjd.rest.dao.role_module.IRole_moduleDao;
import com.yichuang.kyjd.commnd.base.impl.BaseDaoImpl;
import com.yichuang.kyjd.rest.entity.role_module.Role_module;
import com.yichuang.kyjd.rest.entity.user_role.User_role;

/**
 * @author zj default
 * 
 * @version 1.1
 */

@Repository
public class Role_moduleDaoImpl extends BaseDaoImpl<Role_module, Integer>
		implements IRole_moduleDao {
	public List<Role_module> getList(String statement, Role_module t) {
		return super.selectList(statement, t);
	}
	
	
	public List<Object> getModuleCode(String statement, User_role t) {
		return super.selectListObject(statement, t);
	}
	
	public List<Object> getModuleCode(String statement, Role_module t) {
		return super.selectListObject(statement, t);
	}
	
	
	public List<Object> getListParentModuleCode(String statement, Role_module t) {
		return super.selectListObject(statement, t);
	}
	public List<Object> getRoleMenuByRoleCode(String statement, Role_module t) {
		return super.selectListObject(statement, t);
	}
}