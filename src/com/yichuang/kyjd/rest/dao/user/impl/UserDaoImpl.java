package com.yichuang.kyjd.rest.dao.user.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.yichuang.kyjd.rest.dao.user.IUserDao;
import com.yichuang.kyjd.commnd.base.impl.BaseDaoImpl;
import com.yichuang.kyjd.rest.entity.user.User;

/**
 * @author zj default
 * 
 * @version 1.1
 */

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements IUserDao {
	public List<User> getList(String statement, User t) {
		return super.selectList(statement, t);
	}
	
	
	public User getUser(String statement, User t) {
		return (User)super.selectOneObject(statement, t);
	}
}