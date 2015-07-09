package com.yichuang.kyjd.rest.service.specialty_info.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yichuang.kyjd.rest.dao.specialty_info.impl.Specialty_infoDaoImpl;
import com.yichuang.kyjd.rest.service.specialty_info.ISpecialty_infoService;
import com.yichuang.kyjd.commnd.base.impl.BaseServiceImpl;
import com.yichuang.kyjd.rest.entity.specialty_info.Specialty_info;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Service
public class Specialty_infoServiceImpl extends BaseServiceImpl<Specialty_info, Integer> implements ISpecialty_infoService{
  @Autowired
  private Specialty_infoDaoImpl dao;

  public List<Specialty_info> selectSpecialty_info(Specialty_info t){
       return dao.getList("selectSpecialty_info", t);
  }
  
  public Specialty_info save(Specialty_info t){
      return dao.insert(t);
 }
}