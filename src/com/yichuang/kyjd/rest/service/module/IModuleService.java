package com.yichuang.kyjd.rest.service.module;

import java.util.List;
import com.yichuang.kyjd.rest.entity.module.Module;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface IModuleService {
  public List<Module> selectModule(Module t);
  public List<Module> selectModuleAll(Module t);
}