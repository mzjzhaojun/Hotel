package com.yichuang.kyjd.rest.service.dictionaries.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yichuang.kyjd.rest.dao.dictionaries.impl.DictionariesDaoImpl;
import com.yichuang.kyjd.rest.service.dictionaries.IDictionariesService;
import com.yichuang.kyjd.commnd.base.impl.BaseServiceImpl;
import com.yichuang.kyjd.rest.entity.dictionaries.Dictionaries;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Service
public class DictionariesServiceImpl extends BaseServiceImpl<Dictionaries, Integer> implements IDictionariesService{
  @Autowired
  private DictionariesDaoImpl dao;

  public List<Dictionaries> selectDictionaries(Dictionaries t){
       return dao.getList("selectDictionariesByType", t);
  }
  //查询role角色是否存在
	public List<Dictionaries> getListLevel() {
		return dao.getListLevel("getListLevel");
	}
}