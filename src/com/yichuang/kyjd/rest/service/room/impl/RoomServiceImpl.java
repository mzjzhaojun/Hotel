package com.yichuang.kyjd.rest.service.room.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yichuang.kyjd.rest.dao.room.impl.RoomDaoImpl;
import com.yichuang.kyjd.rest.service.room.IRoomService;
import com.yichuang.kyjd.commnd.base.impl.BaseServiceImpl;
import com.yichuang.kyjd.rest.entity.room.Room;

/**
 * @author zj default
 * 
 * @version 1.1
 */

@Service
public class RoomServiceImpl extends BaseServiceImpl<Room, Integer> implements
		IRoomService {
	@Autowired
	private RoomDaoImpl dao;

	public List<Room> selectRoom(Room t) {
		return dao.getList("selectRoom", t);
	}
	
	public List<Room> selectMobileRoom(Room t) {
		return dao.getList("selectMobileRoom", t);
	}
	
	public Object selectRoomSurplus(String rowid) {
		return dao.selectOneObject("selectRoomSurplus", rowid);
	}
	
	public void updateRoom(Room t){
		dao.updateObject("updateRoom", t);
	}
	
	public List<String> getDates(String start, String end) {
		Calendar p_start = Calendar.getInstance();
		Calendar p_end = Calendar.getInstance();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");

		List<String> result = new ArrayList<String>();
		try {
			Date sdate = sdf.parse(start);
			Date edate = sdf.parse(end);
			p_start.setTime(sdate);
			p_end.setTime(edate);
			while (p_start.before(p_end)) {
			    result.add(sdf.format(p_start.getTime()));
			    p_start.add(Calendar.DAY_OF_YEAR, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return result;
    }
	
}