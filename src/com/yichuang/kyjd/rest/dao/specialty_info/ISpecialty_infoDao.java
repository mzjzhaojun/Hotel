package com.yichuang.kyjd.rest.dao.specialty_info;

import java.util.List;
import com.yichuang.kyjd.rest.entity.specialty_info.Specialty_info;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface ISpecialty_infoDao {
  public List<Specialty_info> getList(String statement,Specialty_info t);
}