package com.yichuang.kyjd.commnd.base.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.yichuang.kyjd.commnd.base.IBaseDao;
import com.yichuang.kyjd.commnd.base.SqlSessionDaoSupport;
import com.yichuang.kyjd.commnd.system.exception.BaseDaoException;
import com.yichuang.kyjd.commnd.system.util.bean.BeanMapUtil;
import com.yichuang.kyjd.commnd.system.util.page.GenericDefaultPage;
import com.yichuang.kyjd.commnd.system.util.page.IGenericPage;
import com.yichuang.kyjd.commnd.system.util.reflect.ReflectGeneric;

/**
 * 
 * 1.数据操作封装基于泛型T对象 2.根据myBatis配置文件*.Mapper中namespace的名称操作对应表
 * 3.所有*.Mapper操作ID根据本页中的常量设置
 * 
 * @author zjma
 * 
 */
@Repository
public class BaseDaoImpl<T, ID extends Serializable> extends
		SqlSessionDaoSupport implements IBaseDao<T, ID> {

	public static final String SQLNAME_SEPARATOR = ".";
	public static final String SQL_SAVE = "save";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_GETBYID = "getById";
	public static final String SQL_DELETEBYID = "deleteById";
	public static final String SQL_DELETEBYIDS = "deleteByIds";
	public static final String SQL_FINDPAGEBY = "getPage";
	public static final String SQL_FINDLISTBY = "getList";
	public static final String SQL_GETCOUNTBY = "getCount";
	public static final Integer SERVER_ERROR = HttpStatus.SC_INTERNAL_SERVER_ERROR;
	public static final String SORT_NAME = "SORT";
	public static final String DIR_NAME = "DIR";

	/** 不能用于SQL中的非法字符（主要用于排序字段名） */
	public static final String[] ILLEGAL_CHARS_FOR_SQL = { ",", ";", " ", "\"",
			"%" };

	/**
	 * 获取默认SqlMapping命名空间。
	 * 
	 * @return 返回命名空间字符串
	 */
	@SuppressWarnings("unchecked")
	protected String getDefaultSqlNamespace() {
		Class<T> clazz = ReflectGeneric.getClassGenricType(this.getClass());
		String nameSpace = clazz.getName();
		return nameSpace.substring(
				nameSpace.lastIndexOf(SQLNAME_SEPARATOR) + 1,
				nameSpace.length());
	}

	/**
	 * 将SqlMapping命名空间与给定的SqlMapping名组合在一起。
	 * 
	 */
	protected String getSqlName(String sqlName) {
		return sqlNamespace + SQLNAME_SEPARATOR + sqlName;
	}

	/**
	 * SqlMapping命名空间
	 */
	private String sqlNamespace = getDefaultSqlNamespace();

	/**
	 * 获取SqlMapping命名空间
	 * 
	 * @return SqlMapping命名空间
	 */
	public String getSqlNamespace() {
		return sqlNamespace;
	}

	/**
	 * 设置SqlMapping命名空间.
	 * 
	 * @param sqlNamespace
	 *            SqlMapping命名空间
	 */
	public void setSqlNamespace(String sqlNamespace) {
		this.sqlNamespace = sqlNamespace;
	}

	/**
	 * 生成主键值。 默认情况下什么也不做； 如果需要生成主键，需要由子类重写此方法根据需要的方式生成主键值。
	 * 
	 * @param ob
	 *            要持久化的对象
	 */
	protected void generateId(T t) {
	}

	/**
	 * 新增
	 * 
	 * 继承泛型对象 T
	 * 
	 */
	public T insert(T t) {
		generateId(t);
		try {
			this.getSqlSession().insert(getSqlName(SQL_SAVE), t);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR, "execute =SQL_SAVE= "
					+ getSqlName(SQL_SAVE) + "; Message:" + e.getMessage(),
					true);
		}
		return t;
	}

	/**
	 * 更新
	 * 
	 * 继承泛型对象 T
	 * 
	 */
	public Integer update(T t) {
		try {
			return this.getSqlSession().update(getSqlName(SQL_UPDATE), t);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR, "execute =SQL_UPDATE= "
					+ getSqlName(SQL_UPDATE) + "; Message:" + e.getMessage(),
					true);
		}
	}

	/**
	 * 
	 * 查询
	 * 
	 * 继承泛型对象 T
	 */
	@SuppressWarnings({ "unchecked" })
	public T selectOne(String id) {
		try {
			return (T) this.getSqlSession().selectOne(getSqlName(SQL_GETBYID),
					id);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR, "execute =SQL_GETBYID= "
					+ getSqlName(SQL_GETBYID) + "; Message:" + e.getMessage(),
					true);
		}
	}

	/**
	 * 删除数组
	 * 
	 * 继承泛型对象 T
	 * 
	 */
	public Integer deleteByIds(ID[] ids) {
		try {
			return this.getSqlSession()
					.delete(getSqlName(SQL_DELETEBYIDS), ids);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_DELETEBYIDS= " + getSqlName(SQL_DELETEBYIDS)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 删除单条
	 * 
	 * 继承泛型对象 T
	 * 
	 */
	public Integer deleteById(ID id) {
		try {
			return this.getSqlSession().delete(getSqlName(SQL_DELETEBYID), id);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_DELETEBYID= " + getSqlName(SQL_DELETEBYID)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 分页查询
	 * 
	 * 继承泛型对象 T
	 */
	@SuppressWarnings("unchecked")
	public IGenericPage<T> selectPage(T param, int pageNo, int pageSize,
			String sort, String dir) {
		int count = selectCount(param);
		if (count < 1) {
			return GenericDefaultPage.emptyPage();
		}
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanMapUtil.beanToMap(param);
			if (sort != null) {
				sort = filterIllegalChars(sort, ILLEGAL_CHARS_FOR_SQL);
			}
			if (StringUtils.isEmpty(sort) || StringUtils.isEmpty(dir)) {
			} else {
				paramMap.put(SORT_NAME, sort);
				paramMap.put(DIR_NAME, dir);
			}
			int start = GenericDefaultPage.getStartOfPage(pageNo, pageSize) - 1;
			paramMap.put("pageStart", start);
			paramMap.put("pageEnd", start + pageSize);
			List<T> lst = this.getSqlSession().selectList(
					getSqlName(SQL_FINDPAGEBY), paramMap);
			return new GenericDefaultPage<T>(pageNo, pageSize, lst, count);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_FINDPAGEBY= " + getSqlName(SQL_FINDPAGEBY)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 查询记录数
	 * 
	 * 继承泛型对象 T
	 */
	@SuppressWarnings("unchecked")
	public Integer selectCount(T t) {
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanMapUtil.beanToMap(t);
			return (Integer) this.getSqlSession().selectOne(
					getSqlName(SQL_GETCOUNTBY), paramMap);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_GETCOUNTBY= " + getSqlName(SQL_GETCOUNTBY)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 查询所有数据有排序参数
	 * 
	 * 继承泛型对象 T
	 * 
	 * @param t
	 * @param sort
	 * @param dir
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> selectList(T t, String sort, String dir) {
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanMapUtil.beanToMap(t);
			if (sort != null) {
				sort = filterIllegalChars(sort, ILLEGAL_CHARS_FOR_SQL);
			}
			if (StringUtils.isEmpty(sort) || StringUtils.isEmpty(dir)) {

			} else {
				paramMap.put(SORT_NAME, sort);
				paramMap.put(DIR_NAME, dir);
			}
			return this.getSqlSession().selectList(getSqlName(SQL_FINDLISTBY),
					paramMap);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_FINDLISTBY= " + getSqlName(SQL_FINDLISTBY)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 查询所有数据无排序参数
	 * 
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> selectList(T t) {
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanMapUtil.beanToMap(t);
			return this.getSqlSession().selectList(getSqlName(SQL_FINDLISTBY),
					paramMap);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_FINDLISTBY= " + getSqlName(SQL_FINDLISTBY)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 
	 * 查询所有数据 自定义查询语句ID
	 * 
	 * 继承泛型对象 T
	 * 
	 * @param statement
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> selectList(String statement, Object t) {
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanMapUtil.beanToMap(t);
			return this.getSqlSession().selectList(getSqlName(statement),
					paramMap);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_FINDLISTBY= " + getSqlName(SQL_FINDLISTBY)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 
	 * 插入
	 * 
	 * @param statement
	 *            插入sqlID
	 * @param parameter
	 * @return
	 */
	public Object insertObject(String statement, Object parameter) {
		try {
			this.getSqlSession()
					.insert(getSqlName(statement), parameter);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR, "execute =SQL_SAVE= "
					+ getSqlName(SQL_SAVE) + "; Message:" + e.getMessage(),
					true);
		}
		return parameter;
	}

	/**
	 * 更新
	 * 
	 * @param statement
	 *            更新sqlID
	 * @param parameter
	 * @return
	 */
	public Integer updateObject(String statement, Object parameter) {
		try {
			return this.getSqlSession()
					.update(getSqlName(statement), parameter);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR, "execute =SQL_UPDATE= "
					+ getSqlName(SQL_UPDATE) + "; Message:" + e.getMessage(),
					true);
		}
	}

	/**
	 * 
	 * 单条数据查询
	 * 
	 * @param statement
	 *            查询sqlID
	 * @param parameter
	 * @return
	 */
	public Object selectOneObject(String statement, Object parameter) {
		try {
			return this.getSqlSession().selectOne(getSqlName(statement),
					parameter);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR, "execute =SQL_GETBYID= "
					+ getSqlName(SQL_GETBYID) + "; Message:" + e.getMessage(),
					true);
		}
	}

	/**
	 * 删除数组
	 * 
	 * @param statement
	 *            删除sqlID
	 * @param ids
	 * @return
	 */
	public Integer deleteByIdsObject(String statement, String[] ids) {
		try {
			return this.getSqlSession().delete(getSqlName(statement), ids);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_DELETEBYIDS= " + getSqlName(SQL_DELETEBYIDS)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 删除单条记录
	 * 
	 * @param statement
	 *            删除sqlID
	 * @param id
	 * @return
	 */
	public Integer deleteByIdObject(String statement, String id) {
		try {
			return this.getSqlSession().delete(getSqlName(statement), id);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_DELETEBYID= " + getSqlName(SQL_DELETEBYID)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 分页
	 * 
	 * @param statement
	 *            分页sqlID
	 * @param countstatement
	 *            总数sqlID
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @param sort
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public IGenericPage<Object> selectPageObject(String statement,
			String countstatement, Object param, int pageNo, int pageSize,
			String sort) {
		int count = selectCountObject(countstatement, param);
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
			List<Object> lst = this.getSqlSession().selectList(
					getSqlName(statement), paramMap);
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
	 * @param statement
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer selectCountObject(String statement, Object t) {
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanMapUtil.beanToMap(t);
			return (Integer) this.getSqlSession().selectOne(
					getSqlName(statement), paramMap);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_GETCOUNTBY= " + getSqlName(SQL_GETCOUNTBY)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 查询MAP所有数据
	 * 
	 * @param statement
	 *            查询sqlID
	 * @param t
	 * @param sort
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectListMapObject(String statement, Object t,
			String sort) {
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
			return this.getSqlSession().selectMap(getSqlName(statement), t,
					null);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_FINDLISTBY= " + getSqlName(SQL_FINDLISTBY)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 查询所有数据带排序参数
	 * 
	 * @param statement 查询sqlID
	 * @param t
	 * @param sort
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> selectListObject(String statement, Object t, String sort) {
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
			return this.getSqlSession().selectList(getSqlName(statement),
					paramMap);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_FINDLISTBY= " + getSqlName(SQL_FINDLISTBY)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 查询所有数据不带排序参数
	 * 
	 * @param statement 查询sqlID
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> selectListObject(String statement, Object t) {
		Map<String, Object> paramMap = null;
		try {
			paramMap = BeanMapUtil.beanToMap(t);
			return this.getSqlSession().selectList(getSqlName(statement),
					paramMap);
		} catch (Exception e) {
			throw new BaseDaoException(SERVER_ERROR,
					"execute =SQL_FINDLISTBY= " + getSqlName(SQL_FINDLISTBY)
							+ "; Message:" + e.getMessage(), true);
		}
	}

	/**
	 * 去特殊字符
	 * 
	 * @param str
	 * @param filterChars
	 * @return
	 */
	protected String filterIllegalChars(String str, String[] filterChars) {
		String rs = str;
		if (rs != null && filterChars != null) {
			for (String fc : filterChars) {
				if (fc != null && fc.length() > 0) {
					str = str.replaceAll(fc, "");
				}
			}
		}
		return rs;
	}
}
