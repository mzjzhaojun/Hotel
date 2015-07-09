package com.yichuang.kyjd.rest.service.news_info.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yichuang.kyjd.rest.dao.news_info.impl.News_infoDaoImpl;
import com.yichuang.kyjd.rest.service.news_info.INews_infoService;
import com.yichuang.kyjd.commnd.base.impl.BaseServiceImpl;
import com.yichuang.kyjd.rest.entity.news_info.News_info;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Service
public class News_infoServiceImpl extends BaseServiceImpl<News_info, Integer> implements INews_infoService{
  @Autowired
  private News_infoDaoImpl dao;

  public List<News_info> selectNews_info(News_info t){
       return dao.getList("selectNews_info", t);
  }
}