package com.yichuang.kyjd.rest.service.user_role;

import java.util.List;
import com.yichuang.kyjd.rest.entity.user_role.User_role;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface IUser_roleService {
  public List<User_role> selectUser_role(User_role t);
}