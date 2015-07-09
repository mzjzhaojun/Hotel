package com.yichuang.kyjd.rest.controller.dictionaries;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.yichuang.kyjd.commnd.base.impl.BaseController;
import com.yichuang.kyjd.rest.service.dictionaries.impl.DictionariesServiceImpl;
import com.yichuang.kyjd.rest.entity.dictionaries.Dictionaries;

/**
 * @author zj default test
 * 
 * @version 1.1
 */

@Controller
@RequestMapping("/rest/dictionaries")
public class DictionariesController extends
		BaseController<Dictionaries, Integer> {

	@Autowired
	private DictionariesServiceImpl service;

	@Autowired
	public void setBaseService() {
		setBaseService(service);
	}

	/**
	 * custom
	 * 
	 * @version 1.1
	 */
	@RequestMapping(value = "/type/{parentid}", method = RequestMethod.GET)
	public void customSelect(@PathVariable String parentid, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Dictionaries u = new Dictionaries();
			u.setParentid(parentid);
			List<Dictionaries> list = service.selectDictionaries(u);
			super.setSuccess(list);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}
	
	
	/**
	 * custom
	 * /rest/dictionaries/checkrolename   
	 * @version 1.1
	 */
	@RequestMapping(value = "/getListLevel", method = RequestMethod.GET)
	public void checkUserName(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Dictionaries> list = service.getListLevel();
			super.setSuccess(list);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}
}
