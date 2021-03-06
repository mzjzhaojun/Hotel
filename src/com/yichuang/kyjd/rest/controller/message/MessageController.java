package com.yichuang.kyjd.rest.controller.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.yichuang.kyjd.commnd.base.impl.BaseController;
import com.yichuang.kyjd.commnd.system.util.SubNumUtil;
import com.yichuang.kyjd.commnd.system.util.note.NoteUtil;
import com.yichuang.kyjd.commnd.web.StaticSources;
import net.sf.json.JSONObject;

import com.yichuang.kyjd.rest.service.coupon.impl.CouponServiceImpl;
import com.yichuang.kyjd.rest.service.message.impl.MessageServiceImpl;
import com.yichuang.kyjd.rest.entity.coupon.Coupon;
import com.yichuang.kyjd.rest.entity.message.Message;

/**
 * @author zj default test
 * 
 * @version 1.1
 */

@Controller
@RequestMapping("/rest/message")
public class MessageController extends BaseController<Message, Integer> {

	@Autowired
	private MessageServiceImpl service;
	
	
	@Autowired
	private CouponServiceImpl couponservice;
	
	@Autowired
	public void setBaseService() {
		setBaseService(service);
	}

	/**
	 * custom
	 * 
	 * @version 1.1
	 */
	@RequestMapping(value = "/sendmsg", method = RequestMethod.GET)
	public void sendmsg(HttpServletRequest request, HttpServletResponse response) {
		try {
			String json = request.getParameter(StaticSources.JSONVO);
			Message u = null;
			if (json != null) {
				u = (Message) JSONObject.toBean(JSONObject.fromObject(json),
						Message.class);
			}
			boolean flag = NoteUtil.getInstance().sendMessage("",u.getNo(),
					u.getType(), SubNumUtil.getNumCode());
			super.setSuccess(flag);
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
	@RequestMapping(value = "/checkaccout", method = RequestMethod.GET)
	public void checkmsg(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String json = request.getParameter(StaticSources.JSONVO);
			Message u = null;
			if (json != null) {
				u = (Message) JSONObject.toBean(JSONObject.fromObject(json),
						Message.class);
			}
			
//			Coupon co = (Coupon)couponservice.getByName("现金抵用券");
			super.setSuccess(service.checkMsgAccout(u));
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

}
