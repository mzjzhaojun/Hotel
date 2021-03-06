package com.yichuang.kyjd.rest.dao.reservation.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.yichuang.kyjd.rest.dao.reservation.IReservationDao;
import com.yichuang.kyjd.commnd.base.impl.BaseDaoImpl;
import com.yichuang.kyjd.rest.entity.reservation.Reservation;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Repository
public class ReservationDaoImpl extends BaseDaoImpl<Reservation, Integer> implements IReservationDao{
public List<Reservation> getList(String statement,Reservation t){
  return super.selectList(statement, t);
  }
}