package com.yichuang.kyjd.commnd.base;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * 
 * baseController
 * 
 * @author zjma
 * 
 */
public interface IBaseController<T, ID extends Serializable> {
	/**
	 * 保存（持久化）对象
	 * 
	 * @param o
	 *            要持久化的对象
	 * @return 执行成功的记录个数
	 */
	public void save(HttpServletRequest request, HttpServletResponse JsonResponse,T t);

	/**
	 * 更新（持久化）对象
	 * 
	 * @param o
	 *            要持久化的对象
	 * @return 执行成功的记录个数
	 */
	public void update(@PathVariable String id,HttpServletRequest request,
			HttpServletResponse JsonResponse,T t);

	/**
	 * 获取指定的唯一标识符对应的持久化对象
	 * 
	 * @param id
	 *            指定的唯一标识符
	 * @return 指定的唯一标识符对应的持久化对象，如果没有对应的持久化对象，则返回null。
	 */
	public void getById(@PathVariable String id, HttpServletRequest request,
			HttpServletResponse JsonResponse,T t);

	/**
	 * 删除指定的唯一标识符数组对应的持久化对象
	 * 
	 * @param ids
	 *            指定的唯一标识符数组
	 * @return 删除的对象数量
	 */
	public void deleteByIds(@PathVariable String ids, HttpServletRequest request,
			HttpServletResponse JsonResponse,T t);

	/**
	 * 不分页查询
	 * 
	 * @return 查询结果列表
	 */
	public void getList(HttpServletRequest request, HttpServletResponse JsonResponse,T t);

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 *            查询参数
	 * @return 查询结果列表
	 */
	public void getPage(@PathVariable Integer pageNo,
			@PathVariable Integer pageSize, HttpServletRequest request,
			HttpServletResponse JsonResponse,T t);

}
