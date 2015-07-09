package com.yichuang.kyjd.rest.service.news_info;

import java.util.List;
import com.yichuang.kyjd.rest.entity.news_info.News_info;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface INews_infoService {
  public List<News_info> selectNews_info(News_info t);
}