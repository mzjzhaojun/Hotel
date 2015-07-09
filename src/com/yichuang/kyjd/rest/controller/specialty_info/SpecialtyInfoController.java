package com.yichuang.kyjd.rest.controller.specialty_info;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yichuang.kyjd.commnd.base.impl.BaseController;
import com.yichuang.kyjd.commnd.system.util.DateUtil;
import com.yichuang.kyjd.commnd.web.StaticSources;
import com.yichuang.kyjd.rest.entity.specialty_info.Specialty_info;
import com.yichuang.kyjd.rest.entity.user.User;
import com.yichuang.kyjd.rest.service.specialty_info.impl.Specialty_infoServiceImpl;

/**
 * @author zj default test
 * 
 * @version 1.1
 */

@Controller
@RequestMapping("/rest/specialtyinfo")
public class SpecialtyInfoController extends
		BaseController<Specialty_info, Integer> {

	@Autowired
	private Specialty_infoServiceImpl service;

	@Autowired
	public void setBaseService() {
		setBaseService(service);
	}

	/**
	 * custom
	 * 
	 * @version 1.1
	 */
	@RequestMapping(value = "/param1", method = RequestMethod.GET)
	public void customSelect(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String json = request.getParameter(StaticSources.JSONVO);
			Specialty_info u = null;
			if (json != null) {
				u = (Specialty_info) JSONObject.toBean(
						JSONObject.fromObject(json), Specialty_info.class);
			}
			List<Specialty_info> list = service.selectSpecialty_info(u);
			super.setSuccess(list);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

	/**
	 * custom
	 * 
	 * @version 1.1
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
   public void save(HttpServletRequest request,HttpServletResponse response) {
   	try {
   		 String json = request.getParameter(StaticSources.JSONVO);
   		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("token");
 			 Specialty_info si = null;
 			 if (json != null) {
 				si = (Specialty_info) JSONObject.toBean(JSONObject.fromObject(json),Specialty_info.class);
 			 }
 			 si.setAdd_user(user.getUser_name());
 			 
 			si.setAdd_date(DateUtil.currDayData(new Date(),	"yyyy-MM-dd hh:mm:ss"));
   		 service.save(si);
   	}catch (Exception e) {
   		super.setError(e.getMessage());
       }
   	super.ResponseJson(response);
   }
}
