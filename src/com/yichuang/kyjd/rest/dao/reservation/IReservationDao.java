package com.yichuang.kyjd.rest.dao.reservation;

import java.util.List;
import com.yichuang.kyjd.rest.entity.reservation.Reservation;

/**
* @author zj    default  
* 
* @version 1.1
*/

public interface IReservationDao {
  public List<Reservation> getList(String statement,Reservation t);
}