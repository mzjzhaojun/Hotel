package com.yichuang.kyjd.commnd.base.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yichuang.kyjd.commnd.base.IBaseService;
import com.yichuang.kyjd.commnd.system.util.page.IGenericPage;

/**
 * 
 * 实现类
 * 
 * @author zjma
 * 
 */
@Service
public class BaseServiceImpl<T, ID extends Serializable> implements
		IBaseService<T, ID> {
	
	@Autowired
	private BaseDaoImpl<T, ID> baseDao;

	@Override
	public T insert(T t) {
		return baseDao.insert(t);
	}

	@Override
	public Integer update(T t) {
		return baseDao.update(t);
	}

	@Override
	public T selectOne(String id) {
		return baseDao.selectOne(id);
	}

	@Override
	public Integer deleteByIds(ID[] ids) {
		return baseDao.deleteByIds(ids);
	}

	@Override
	public IGenericPage<T> selectPage(T t, int pageNo, int pageSize,
			String sort, String dir) {
		return baseDao.selectPage(t,pageNo,pageSize,sort,dir);
	}

	@Override
	public Integer selectCount(T t) {
		return baseDao.selectCount(t);
	}

	@Override
	public List<T> selectList(T t, String sort, String dir) {
		return baseDao.selectList(t,sort,dir);
	}
	
	@Override
	public List<T> selectList(T t){
		return baseDao.selectList(t);
	}
}
