package com.yichuang.kyjd.rest.dao.dictionaries.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.yichuang.kyjd.rest.dao.dictionaries.IDictionariesDao;
import com.yichuang.kyjd.commnd.base.impl.BaseDaoImpl;
import com.yichuang.kyjd.rest.entity.dictionaries.Dictionaries;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Repository
public class DictionariesDaoImpl extends BaseDaoImpl<Dictionaries, Integer> implements IDictionariesDao{
public List<Dictionaries> getList(String statement,Dictionaries t){
  return super.selectList(statement, t);
  }
public List<Dictionaries> getListLevel(String statement) {
	return super.selectList(statement,null);
}
}