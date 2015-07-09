package com.yichuang.kyjd.rest.controller.order_info;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yichuang.kyjd.commnd.alipay.config.AlipayConfig;
import com.yichuang.kyjd.commnd.alipay.util.AlipaySubmit;
import com.yichuang.kyjd.commnd.alipay.util.UtilDate;
import com.yichuang.kyjd.commnd.base.impl.BaseController;
import com.yichuang.kyjd.commnd.system.util.SubNumUtil;
import com.yichuang.kyjd.commnd.web.StaticSources;
import net.sf.json.JSONObject;
import com.yichuang.kyjd.rest.service.order_info.impl.Order_infoServiceImpl;
import com.yichuang.kyjd.rest.entity.customer.Customer;
import com.yichuang.kyjd.rest.entity.order_info.Order;
import com.yichuang.kyjd.rest.entity.order_info.Order_info;

/**
 * @author zj default test
 * 
 * @version 1.1
 */

@Controller
@RequestMapping("/rest/orderinfo")
public class OrderInfoController extends BaseController<Order_info, Integer> {

	@Autowired
	private Order_infoServiceImpl service;

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
			Order_info u = null;
			if (json != null) {
				u = (Order_info) JSONObject.toBean(JSONObject.fromObject(json),
						Order_info.class);
			}
			List<Order_info> list = service.selectOrder_info(u);
			super.setSuccess(list);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

	@RequestMapping(value = "/submitorder", method = RequestMethod.GET)
	public void submitorder(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			//String address = request.getLocalAddr();
			String address = "www.chongmingregency.com";
			///////////////////////////////////////////存入订单/////////////////////////////////////////////
			HttpSession session = request.getSession();
			Customer cust = (Customer) session.getAttribute("cust");
			String json = request.getParameter(StaticSources.JSONVO);
			Order u = null;
			if (json != null) {
				u = (Order) JSONObject.toBean(JSONObject.fromObject(new String(
						json.getBytes("ISO-8859-1"), "UTF-8")), Order.class);
			}
			u.setAccount(cust.getAccout()); //登陆用户
			u.setNo(SubNumUtil.getSubNumCode()); //订单号
			String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";
			String format = "xml";
			String v = "2.0";
			String req_id = UtilDate.getOrderNum();
			String notify_url = "http://" + address + "/Hotel/rest/pay/notify";
			String call_back_url = "http://" + address+ "/Hotel/rest/pay/callback";
//			String notify_url = "http://" + address + "/Hotel_web/html/microLetter/notify_url.jsp";
//			String call_back_url = "http://" + address+ "/Hotel_web/html/microLetter/call_back_url.jsp";
			String merchant_url = "http://"+address+"/Hotel_web/html/microLetter/index.html";
			Map<String, String> sParaTemp;
			String out_trade_no = u.getNo();
			String subject = u.getTitle();
			String total_fee = u.getSumamounts();
			String req_dataToken = "<direct_trade_create_req><notify_url>"
					+ notify_url + "</notify_url><call_back_url>"
					+ call_back_url + "</call_back_url><seller_account_name>"
					+ AlipayConfig.seller_email
					+ "</seller_account_name><out_trade_no>" + out_trade_no
					+ "</out_trade_no><subject>" + subject
					+ "</subject><total_fee>" + total_fee
					+ "</total_fee><merchant_url>" + merchant_url
					+ "</merchant_url></direct_trade_create_req>";
			// 把请求参数打包成数组
			Map<String, String> sParaTempToken = new HashMap<String, String>();
			sParaTempToken.put("service", "alipay.wap.trade.create.direct");
			sParaTempToken.put("partner", AlipayConfig.partner);
			sParaTempToken.put("_input_charset", AlipayConfig.input_charset);
			sParaTempToken.put("sec_id", AlipayConfig.sign_type);
			sParaTempToken.put("format", format);
			sParaTempToken.put("v", v);
			sParaTempToken.put("req_id", req_id);
			sParaTempToken.put("req_data", req_dataToken);
			// 建立请求
			String sHtmlTextToken = AlipaySubmit.buildRequest(
					ALIPAY_GATEWAY_NEW, "", "", sParaTempToken);
			// URLDECODE返回的信息
			sHtmlTextToken = URLDecoder.decode(sHtmlTextToken,
					AlipayConfig.input_charset);
			// 获取token
			String request_token = AlipaySubmit.getRequestToken(sHtmlTextToken);
			// 业务详细
			String req_data = "<auth_and_execute_req><request_token>"
					+ request_token + "</request_token></auth_and_execute_req>";
			sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("sec_id", AlipayConfig.sign_type);
			sParaTemp.put("format", format);
			sParaTemp.put("v", v);
			sParaTemp.put("req_data", req_data);
			// 建立请求
			String sHtmlText = AlipaySubmit.buildRequest(ALIPAY_GATEWAY_NEW,
					sParaTemp, "get", "确认");
			service.insertOrder_info(u);
			super.setSuccess(sHtmlText);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.ResponseJson(response);
	}

	@RequestMapping(value = "/privatesubmitorder", method = RequestMethod.GET)
	public void privatesubmitorder(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String json = request.getParameter(StaticSources.JSONVO);
			Order u = null;
			if (json != null) {
				u = (Order) JSONObject.toBean(JSONObject.fromObject(new String(
						json.getBytes("ISO-8859-1"), "UTF-8")), Order.class);
			}
			u.setNo(SubNumUtil.getSubNumCode());
			super.setSuccess(service.insertOrder_info(u));
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

	@RequestMapping(value = "/order/{type}", method = RequestMethod.GET)
	public void roomorder(@PathVariable String type,HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			Customer cust = (Customer) session.getAttribute("cust");
			Order u = new Order();
			u.setType(type);
			u.setAccount(cust.getAccout());
			super.setSuccess(service.selectOrderRoom(u));
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

	@RequestMapping(value = "/selectOrder", method = RequestMethod.GET)
	public void selectOrder(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String json = request.getParameter(StaticSources.JSONVO);
			Order u = null;
			if (json != null) {
				u = (Order) JSONObject.toBean(JSONObject.fromObject(json),
						Order.class);
			}
			super.setSuccess(service.selectOrder(u));
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

	@RequestMapping(value = "/updateorder", method = RequestMethod.GET)
	public void updateOrder(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String json = request.getParameter(StaticSources.JSONVO);
			Order u = null;
			if (json != null) {
				u = (Order) JSONObject.toBean(JSONObject.fromObject(new String(
						json.getBytes("ISO-8859-1"), "UTF-8")), Order.class);
			}
			service.updateOrder(u);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}
	
	@RequestMapping(value = "/selectOrderInfo/{no}", method = RequestMethod.GET)
	public void selectOrderInfo(@PathVariable String no,HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Order u = new Order();
			u.setNo(no);
			super.setSuccess(service.selectOrderInfo(u));
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}
	
	
	@RequestMapping(value = "/deleteid/{id}", method = RequestMethod.GET)
	public void delete(@PathVariable String id,HttpServletRequest request,
			HttpServletResponse response) {
		try {
			super.setSuccess(service.deleteid(id));
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}
	
}
