package com.yichuang.kyjd.commnd.base;

import java.io.Serializable;
import java.util.List;

import com.yichuang.kyjd.commnd.system.util.page.IGenericPage;


/**
 * 
 * baseservice
 * 
 * @author zjma
 * 
 */
public interface IBaseService<T, ID extends Serializable> {

	/**
     * 保存（持久化）对象
     * @param o 要持久化的对象
     * @return 执行成功的记录个数
     */
	public T insert(T t);

    /**
     * 更新（持久化）对象
     * @param o 要持久化的对象
     * @return 执行成功的记录个数
     */
	public Integer update(T t);

    /**
     * 获取指定的唯一标识符对应的持久化对象
     *
     * @param id 指定的唯一标识符
     * @return 指定的唯一标识符对应的持久化对象，如果没有对应的持久化对象，则返回null。
     */
	public T selectOne(String id);


    /**
     * 删除指定的唯一标识符数组对应的持久化对象
     *
     * @param ids 指定的唯一标识符数组
	 * @return 删除的对象数量
     */
	public Integer deleteByIds(ID[] ids);

	/**
	 * 分页查询
	 * 
	 * @param param 查询参数
	 * @param pageNo 要查询的页号
	 * @param pageSize 每页数据个数
	 * @param sort 排序字段名
	 * @param dir 排序方式（升序(asc)或降序(desc)
	 * @return 查询结果分页数据
	 */
	public IGenericPage<T> selectPage(
			T t, int pageNo, int pageSize, String sort, String dir);

	/**
	 * 获取满足查询参数条件的数据总数
	 * 
	 * @param param 查询参数
	 * @return 数据总数
	 */
	public Integer selectCount(T t);

	/**
	 * 不分页查询
	 * 
	 * @param param 查询参数
	 * @param sort 排序字段名
	 * @param dir 排序方式（升序(asc)或降序(desc)
	 * @return 查询结果列表
	 */
	public List<T> selectList(T t, String sort, String dir);
	
	
	public List<T> selectList(T t);
}
