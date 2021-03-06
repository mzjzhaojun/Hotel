package com.yichuang.kyjd.rest.service.message.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yichuang.kyjd.rest.dao.message.impl.MessageDaoImpl;
import com.yichuang.kyjd.rest.service.message.IMessageService;
import com.yichuang.kyjd.commnd.base.impl.BaseServiceImpl;
import com.yichuang.kyjd.rest.entity.message.Message;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Service
public class MessageServiceImpl extends BaseServiceImpl<Message, Integer> implements IMessageService{
  @Autowired
  private MessageDaoImpl dao;

  public List<Message> selectMessage(Message t){
       return dao.getList("selectMessage", t);
  }
  
  public Object checkMsgAccout(Message t){
      return dao.selectOneObject("checkMsgAccout", t);
 }
}