package com.yichuang.kyjd.rest.service.user_role.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yichuang.kyjd.rest.dao.user_role.impl.User_roleDaoImpl;
import com.yichuang.kyjd.rest.service.user_role.IUser_roleService;
import com.yichuang.kyjd.commnd.base.impl.BaseServiceImpl;
import com.yichuang.kyjd.rest.entity.user_role.User_role;

/**
 * @author zj default
 * 
 * @version 1.1
 */

@Service
public class User_roleServiceImpl extends BaseServiceImpl<User_role, Integer>
		implements IUser_roleService {
	@Autowired
	private User_roleDaoImpl dao;

	public List<User_role> selectUser_role(User_role t) {
		return dao.getList("selectUser_role", t);
	}
	
	public User_role getUserRole(User_role t){
		return dao.getUserRole("selectUserRole",t);
	}
	/**
	 * 根据角色code删除关联信息
	 */
	public void deleteByUserCode(String  t){
		 dao.deleteByIdObject("deleteByUserCode", t);
	}
}