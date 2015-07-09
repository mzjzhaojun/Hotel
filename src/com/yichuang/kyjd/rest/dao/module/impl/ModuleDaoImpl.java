package com.yichuang.kyjd.rest.dao.module.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.yichuang.kyjd.rest.dao.module.IModuleDao;
import com.yichuang.kyjd.commnd.base.impl.BaseDaoImpl;
import com.yichuang.kyjd.rest.entity.module.Module;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Repository
public class ModuleDaoImpl extends BaseDaoImpl<Module, Integer> implements IModuleDao{
public List<Module> getList(String statement,Module t){
  return super.selectList(statement, t);
  }
}