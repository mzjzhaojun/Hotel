package com.yichuang.kyjd.rest.dao.message.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.yichuang.kyjd.rest.dao.message.IMessageDao;
import com.yichuang.kyjd.commnd.base.impl.BaseDaoImpl;
import com.yichuang.kyjd.rest.entity.message.Message;

/**
 * @author zj default
 * 
 * @version 1.1
 */

@Repository
public class MessageDaoImpl extends BaseDaoImpl<Message, Integer> implements
		IMessageDao {
	public List<Message> getList(String statement, Message t) {
		return super.selectList(statement, t);
	}
}