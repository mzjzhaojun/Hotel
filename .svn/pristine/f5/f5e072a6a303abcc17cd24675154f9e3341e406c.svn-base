package com.yichuang.kyjd.commnd.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yichuang.kyjd.commnd.base.impl.BaseController;
import com.yichuang.kyjd.commnd.system.exception.BaseException;


/**
 * @author data default test
 * 
 * @version 1.1
 */

@Controller
@RequestMapping("/data")
public class DataSources extends BaseController<Data,Integer> {

//	private Zj data = Zj.getInstance();
	private Flex data = Flex.getInstance();
	/**
	 * getbyId
	 * 
	 * @version 1.1
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public void getById(@PathVariable String name, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			super.setSuccess(data.getListsByName(name));
		} catch (BaseException e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

	/**
	 * getList
	 * 
	 * @version 1.1
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET)
	public void getList(HttpServletRequest request, HttpServletResponse response,Data d) {
		try {
			super.setSuccess(data.getLists());
		} catch (BaseException e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public void getTable(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			super.setSuccess(data.getTableMySql());
		} catch (BaseException e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}
	
	@RequestMapping(value = "/excute", method = RequestMethod.POST)
	public void excute(HttpServletRequest request,
			HttpServletResponse response) {
		String json = request.getParameter(StaticSources.JSONVO);
		
		try {
			JSONObject jobj = JSONObject.fromObject(json);
			String tables = jobj.get("name").toString();
			data.printFileMySql(tables);
			super.setSuccess(null);
		} catch (BaseException e) {
			super.setError(e.getMessage());
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}
	

}
