package com.yichuang.kyjd.rest.dao.news_info;

import java.util.List;
import com.yichuang.kyjd.rest.entity.news_info.News_info;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface INews_infoDao {
  public List<News_info> getList(String statement,News_info t);
}