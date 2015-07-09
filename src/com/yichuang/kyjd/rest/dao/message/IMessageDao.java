package com.yichuang.kyjd.rest.dao.message;

import java.util.List;
import com.yichuang.kyjd.rest.entity.message.Message;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface IMessageDao {
  public List<Message> getList(String statement,Message t);
}