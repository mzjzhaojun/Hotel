package com.yichuang.kyjd.rest.service.reservation.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yichuang.kyjd.rest.dao.reservation.impl.ReservationDaoImpl;
import com.yichuang.kyjd.rest.service.reservation.IReservationService;
import com.yichuang.kyjd.commnd.base.impl.BaseServiceImpl;
import com.yichuang.kyjd.rest.entity.reservation.Reservation;

/**
* @author zj    default  
* 
* @version 1.1
*/

@Service
public class ReservationServiceImpl extends BaseServiceImpl<Reservation, Integer> implements IReservationService{
  @Autowired
  private ReservationDaoImpl dao;

  public List<Reservation> selectReservation(Reservation t){
       return dao.getList("selectReservation", t);
  }
}