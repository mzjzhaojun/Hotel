package com.yichuang.kyjd.rest.service.module.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yichuang.kyjd.rest.dao.module.impl.ModuleDaoImpl;
import com.yichuang.kyjd.rest.service.module.IModuleService;
import com.yichuang.kyjd.commnd.base.impl.BaseServiceImpl;
import com.yichuang.kyjd.rest.entity.module.Module;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Service
public class ModuleServiceImpl extends BaseServiceImpl<Module, Integer> implements IModuleService{
  @Autowired
  private ModuleDaoImpl dao;

  public List<Module> selectModule(Module t){
       return dao.getList("selectModule", t);
  }
  
  public List<Module> selectModuleAll(Module t){
	  return dao.getList("selectModuleAll", t);
  }
}