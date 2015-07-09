package com.yichuang.kyjd.rest.dao.role;

import java.util.List;
import com.yichuang.kyjd.rest.entity.role.Role;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface IRoleDao {
  public List<Role> getList(String statement,Role t);
}