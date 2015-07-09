package com.yichuang.kyjd.rest.service.room;

import java.util.List;
import com.yichuang.kyjd.rest.entity.room.Room;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface IRoomService {
  public List<Room> selectRoom(Room t);
}