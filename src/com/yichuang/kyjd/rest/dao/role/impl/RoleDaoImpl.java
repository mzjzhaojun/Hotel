package com.yichuang.kyjd.rest.dao.role.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.yichuang.kyjd.rest.dao.role.IRoleDao;
import com.yichuang.kyjd.commnd.base.impl.BaseDaoImpl;
import com.yichuang.kyjd.rest.entity.role.Role;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role, Integer> implements IRoleDao{
public List<Role> getList(String statement,Role t){
  return super.selectList(statement, t);
  }
	public Role getRole(String statement, Role t) {
		return (Role)super.selectOneObject(statement, t);
	}
}