package com.yichuang.kyjd.rest.dao.news_info.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.yichuang.kyjd.rest.dao.news_info.INews_infoDao;
import com.yichuang.kyjd.commnd.base.impl.BaseDaoImpl;
import com.yichuang.kyjd.rest.entity.news_info.News_info;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Repository
public class News_infoDaoImpl extends BaseDaoImpl<News_info, Integer> implements INews_infoDao{
public List<News_info> getList(String statement,News_info t){
  return super.selectList(statement, t);
  }
}