package com.yichuang.kyjd.rest.dao.room.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.yichuang.kyjd.rest.dao.room.IRoomDao;
import com.yichuang.kyjd.commnd.base.impl.BaseDaoImpl;
import com.yichuang.kyjd.rest.entity.room.Room;

/**
 * @author zj default
 * 
 * @version 1.1
 */

@Repository
public class RoomDaoImpl extends BaseDaoImpl<Room, Integer> implements IRoomDao {
	
	public List<Room> getList(String statement, Room t) {
		return super.selectList(statement, t);
	}
	
}