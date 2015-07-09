package com.yichuang.kyjd.commnd.base.impl;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.util.StringUtils;

import com.yichuang.kyjd.commnd.base.BaseEntity;
import com.yichuang.kyjd.commnd.system.exception.BaseDaoException;
import com.yichuang.kyjd.commnd.system.util.bean.BeanMapUtil;
import com.yichuang.kyjd.commnd.system.util.page.GenericDefaultPage;
import com.yichuang.kyjd.commnd.system.util.page.IGenericPage;

public class BaseCommonDao extends BaseDaoImpl<BaseEntity, Integer> {

	public BaseCommonDao(SqlSessionFactory sqlSessionFactory) {
		this.setSqlSessionFactory(sqlSessionFactory);
	}
	
	
	/**
	 * 
	 * 插入
	 * 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int insertstatement(String insertstatement, Object parameter) {
		try {
			return this.getSqlSession().insert(insertstatement, parameter);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR, "execute =SQL_SAVE= "
					+ insertstatement + "; Message:" + e.getMessage(),
					true);
		}
	}
	

	/**
	 * 更新
	 * 
	 */
	public Integer updatestatement(String updatestatement, Object parameter) {
		try {
			return this.getSqlSession().update(updatestatement, parameter);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR, "execute =SQL_UPDATE= "
					+ getSqlName(SQL_UPDATE) + "; Message:" + e.getMessage(),
					true);
		}
	}

	/**
	 * 
	 * 单条数据查询
	 */
	public Object selectstatement(String selectstatement, Object parameter) {
		try {
			return (Object) this.getSqlSession().selectOne(selectstatement, parameter);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR, "execute =SQL_GETBYID= "
					+ getSqlName(SQL_GETBYID) + "; Message:" + e.getMessage(),
					true);
		}
	}

	/**
	 * 删除数组
	 * 
	 */
	public Integer deletestatement(String deletestatement, String [] ids) {
		try {
			return this.getSqlSession().delete(deletestatement, ids);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_DELETEBYIDS= " + getSqlName(SQL_DELETEBYIDS)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 删除单挑记录
	 * 
	 */
	public Integer deletestatement(String deletestatement, String id) {
		try {
			return this.getSqlSession().delete(deletestatement, id);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_DELETEBYID= " + getSqlName(SQL_DELETEBYID)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 分页
	 * 
	 */
	@SuppressWarnings("unchecked")
	public IGenericPage<Object> selectpagestatement(String selectpagestatement,String countstatement, Object param,
			int pageNo, int pageSize, String sort) {
		int count = selectcountstatement(countstatement,param);
		if (count < 1) {
			return GenericDefaultPage.emptyPage();
		}
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanMapUtil.beanToMap(param);
			if (sort != null) {
				sort = filterIllegalChars(sort, ILLEGAL_CHARS_FOR_SQL);
			}
			if (StringUtils.isEmpty(sort)) {
			} else {
				paramMap.put(SORT_NAME, sort);
			}
			int start = GenericDefaultPage.getStartOfPage(pageNo, pageSize) - 1;
			paramMap.put("pageStart", start);
			paramMap.put("pageEnd", start + pageSize);
			List<Object> lst = this.getSqlSession().selectList(selectpagestatement,
					paramMap);
			return new GenericDefaultPage<Object>(pageNo, pageSize, lst, count);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_FINDPAGEBY= " + getSqlName(SQL_FINDPAGEBY)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 查询记录数
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Integer selectcountstatement(String selectcountstatement, Object t) {
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanMapUtil.beanToMap(t);
			return (Integer) this.getSqlSession()
					.selectOne(selectcountstatement, paramMap);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_GETCOUNTBY= " + getSqlName(SQL_GETCOUNTBY)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 查询MAP所有数据
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> selectListstatement(String selectListstatement, Object t, String sort) {
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanMapUtil.beanToMap(t);
			if (sort != null) {
				sort = filterIllegalChars(sort, ILLEGAL_CHARS_FOR_SQL);
			}
			if (StringUtils.isEmpty(sort)) {

			} else {
				paramMap.put(SORT_NAME, sort);
			}
			return this.getSqlSession().selectMap(selectListstatement, t, null);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_FINDLISTBY= " + getSqlName(SQL_FINDLISTBY)
							+ "; Message:" + e.getMessage(), true);
		}
	}
	
	/**
	 * 查询所有数据
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Object> selectliststatement(String selectliststatement, Object t, String sort) {
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanMapUtil.beanToMap(t);
			if (sort != null) {
				sort = filterIllegalChars(sort, ILLEGAL_CHARS_FOR_SQL);
			}
			if (StringUtils.isEmpty(sort)) {

			} else {
				paramMap.put(SORT_NAME, sort);
			}
			return this.getSqlSession().selectList(selectliststatement, paramMap);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_FINDLISTBY= " + getSqlName(SQL_FINDLISTBY)
							+ "; Message:" + e.getMessage(), true);
		}
	}
	
	
	/**
	 * 查询所有数据
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Object> selectListObject(String selectListstatement, Object t) {
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanMapUtil.beanToMap(t);
			return this.getSqlSession().selectList(selectListstatement, paramMap);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_FINDLISTBY= " + getSqlName(SQL_FINDLISTBY)
							+ "; Message:" + e.getMessage(), true);
		}
	}
}
