package com.yichuang.kyjd.rest.service.message;

import java.util.List;
import com.yichuang.kyjd.rest.entity.message.Message;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface IMessageService {
  public List<Message> selectMessage(Message t);
}