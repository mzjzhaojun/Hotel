package com.yichuang.kyjd.rest.controller.sellcoupon;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yichuang.kyjd.commnd.alipay.config.AlipayConfig;
import com.yichuang.kyjd.commnd.alipay.util.AlipayNotify;
import com.yichuang.kyjd.commnd.alipay.util.AlipaySubmit;
import com.yichuang.kyjd.commnd.alipay.util.UtilDate;
import com.yichuang.kyjd.commnd.base.impl.BaseController;
import com.yichuang.kyjd.commnd.system.util.DateUtil;
import com.yichuang.kyjd.commnd.system.util.SubNumUtil;
import com.yichuang.kyjd.commnd.system.util.md5.MD5Util;
import com.yichuang.kyjd.commnd.system.util.note.NoteUtil;
import com.yichuang.kyjd.commnd.web.StaticSources;

import net.sf.json.JSONObject;

import com.yichuang.kyjd.rest.service.coupon.impl.CouponServiceImpl;
import com.yichuang.kyjd.rest.service.sellcoupon.impl.SellcouponServiceImpl;
import com.yichuang.kyjd.rest.entity.coupon.Coupon;
import com.yichuang.kyjd.rest.entity.customer.Customer;
import com.yichuang.kyjd.rest.entity.order_info.Order;
import com.yichuang.kyjd.rest.entity.room.Room;
import com.yichuang.kyjd.rest.entity.sellcoupon.Sellcoupon;

/**
 * @author zj default test
 * 
 * @version 1.1
 */

@Controller
@RequestMapping("/rest/sellcoupon")
public class SellcouponController extends BaseController<Sellcoupon, Integer> {

	@Autowired
	private SellcouponServiceImpl service;

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
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void customSelect(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Sellcoupon u = new Sellcoupon();
			HttpSession session = request.getSession();
			Customer cust = (Customer) session.getAttribute("cust");
			u.setUsercode(cust.getAccout());
			List<Sellcoupon> list = service.selectSellcoupon(u);
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
	@RequestMapping(value = "/checksurplus", method = RequestMethod.GET)
	public void checksurplus(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String json = request.getParameter(StaticSources.JSONVO);
			Room u = null;
			if (json != null) {
				u = (Room) JSONObject.toBean(JSONObject.fromObject(json),
						Room.class);
			}
			Object obj = couponservice.selectCouponSurplus(u.getRowid()
					.toString());
			super.setSuccess(obj);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

	@RequestMapping(value = "/submitorder", method = RequestMethod.GET)
	public void submitorder(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// String address = "121.237.48.21";//request.getLocalAddr();
			String address = "www.chongmingregency.com";
			// /////////////////////////////////////////存入订单/////////////////////////////////////////////
			HttpSession session = request.getSession();
			Customer cust = (Customer) session.getAttribute("cust");
			String json = request.getParameter(StaticSources.JSONVO);
			Sellcoupon u = null;
			if (json != null) {
				u = (Sellcoupon) JSONObject.toBean(JSONObject
						.fromObject(new String(json.getBytes("ISO-8859-1"),
								"UTF-8")), Sellcoupon.class);
			}
			u.setUsercode(cust.getAccout()); // 登陆用户
			u.setCode(SubNumUtil.getSubNumCode()); // 订单号
			String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/app.htm?";
			String format = "xml";
			String v = "2.0";
			String req_id = UtilDate.getOrderNum();
			String notify_url = "http://" + address
					+ "/Hotel/rest/sellcoupon/notify";
			String call_back_url = "http://" + address
					+ "/Hotel/rest/sellcoupon/callback";
			// String notify_url = "http://" + address +
			// "/Hotel_web/html/microLetter/notify_url.jsp";
			// String call_back_url = "http://" + address+
			// "/Hotel_web/html/microLetter/call_back_url.jsp";
			String merchant_url = "http://" + address
					+ "/Hotel_web/html/microLetter/index.html";
			Map<String, String> sParaTemp;
			String out_trade_no = u.getCode();
			String subject = u.getName();
			String total_fee = new String(u.getBuyprice() + "");
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
			service.insert(u);
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

	/**
	 * paymoney
	 * 
	 * @version 1.1
	 */
	@RequestMapping(value = "/paymoney", method = RequestMethod.GET)
	public void paymoney(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String address = "www.chongmingregency.com";
			// String address = "121.237.48.21";//request.getLocalAddr();
			String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/app.htm?";
			String format = "xml";
			String v = "2.0";
			String req_id = UtilDate.getOrderNum();
			String notify_url = "http://" + address
					+ "/Hotel/rest/sellcoupon/notify";
			String call_back_url = "http://" + address
					+ "/Hotel/rest/sellcoupon/callback";
			// String notify_url = "http://" + address +
			// "/Hotel_web/html/microLetter/notify_url.jsp";
			// String call_back_url = "http://" + address+
			// "/Hotel_web/html/microLetter/call_back_url.jsp";
			String merchant_url = "http://" + address
					+ "/Hotel_web/html/microLetter/index.html";
			Map<String, String> sParaTemp;
			String rowid = new String(request.getParameter("rowid").getBytes(
					"ISO-8859-1"), "UTF-8");
			String out_trade_no = SubNumUtil.getSubNumCode();
			String subject = new String(request.getParameter("subject")
					.getBytes("ISO-8859-1"), "UTF-8");
			String total_fee = new String(request.getParameter("total_fee")
					.getBytes("ISO-8859-1"), "UTF-8");
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
			// out.println(request_token);
			// 业务详细
			String req_data = "<auth_and_execute_req><request_token>"
					+ request_token + "</request_token></auth_and_execute_req>";
			// 必填
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
			Sellcoupon u = new Sellcoupon();
			u.setCode(out_trade_no);
			u.setRowid(Integer.parseInt(rowid));
			service.updateSellcouponCode(u);
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

	/**
	 * notify
	 * 
	 * @version 1.1
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/notify", method = RequestMethod.GET)
	public void notify(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			PrintWriter out = response.getWriter();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter
					.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
				// "gbk");
				params.put(name, valueStr);
			}

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//

			// RSA签名解密
			if (AlipayConfig.sign_type.equals("0001")) {
				params = AlipayNotify.decrypt(params);
			}
			// XML解析notify_data数据
			Document doc_notify_data = DocumentHelper.parseText(params
					.get("notify_data"));

			// 商户订单号
			// String out_trade_no = doc_notify_data.selectSingleNode(
			// "//notify/out_trade_no" ).getText();

			// 支付宝交易号
			// String trade_no = doc_notify_data.selectSingleNode(
			// "//notify/trade_no" ).getText();

			// 交易状态
			String trade_status = doc_notify_data.selectSingleNode(
					"//notify/trade_status").getText();
			if (AlipayNotify.verifyNotify(params)) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码

				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

				if (trade_status.equals("TRADE_FINISHED")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序

					// 注意：
					// 该种交易状态只在两种情况下出现
					// 1、开通了普通即时到账，买家付款成功后。
					// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
					out.println("success"); // 请不要修改或删除
				} else if (trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序

					// 注意：
					// 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。

					out.println("success"); // 请不要修改或删除
				}

				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

				// ////////////////////////////////////////////////////////////////////////////////////////
			} else {// 验证失败
				out.println("fail");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * callback
	 * 
	 * @version 1.1
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	public void callback(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			String address = "www.chongmingregency.com";
			// String address = "121.237.48.21";//request.getLocalAddr();
			PrintWriter out = response.getWriter();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter
					.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = new String(request.getParameter(
					"out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			/*
			 * String trade_no = new String(request.getParameter("trade_no")
			 * .getBytes("ISO-8859-1"), "UTF-8");
			 */
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			// 计算得出通知验证结果
			boolean verify_result = AlipayNotify.verifyReturn(params);
			if (verify_result) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码
				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序
				// 该页面可做页面美工编辑
				Sellcoupon o = new Sellcoupon();
				o.setCode(out_trade_no);
				o.setSatus("D__1xlxuvvs2jiq");
				String pwd = SubNumUtil.getNumCode();
				o.setPwd(MD5Util.string2MD5(pwd));// 设置券密码
				o.setDuedate(SubNumUtil.getAddMoreDay(90));
				service.updateSellcoupon(o);
				o = (Sellcoupon) service.selectOneSellcoupon(o);
				Coupon co = (Coupon) couponservice.getByName(o.getName());
				Integer surplus = co.getSurplus() - o.getBuycount();
				co.setSurplus(surplus);
				couponservice.updateCoupon(co);
				String message = " " + o.getCode() + "，密码：" + pwd + "";
				NoteUtil.getInstance().sendMessage(o.getName(),
						o.getUsercode(), "4", new String(message));
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset='UTF-8'>");
				out.println("<title>load</title>");
				out.println("<script language='javascript' type='text/javascript'>");
				// out.println("alert('支付成功，返回我的订单！')");
				out.println("window.location.href = 'http://"
						+ address
						+ "/Hotel_web/html/microLetter/main.html?login=sellcoupon';");
				out.println("</script>");
				out.println("</head>");
				out.println("<body>");
				out.println("</body>");
				out.println("</html>");
				// 付款完成
				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			} else {
				// 该页面可做页面美工编辑
				Sellcoupon o = new Sellcoupon();
				o.setCode(out_trade_no);
				o.setSatus("D__u8oqd5onsrbj");
				service.updateSellcoupon(o);
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta charset='UTF-8'>");
				out.println("<title>load</title>");
				out.println("<script language='javascript' type='text/javascript'>");
				// out.println("alert('支付失败，返回我的订单！')");
				out.println("window.location.href = 'http://"
						+ address
						+ "/Hotel_web/html/microLetter/main.html?login=sellcoupon';");
				out.println("</script>");
				out.println("</head>");
				out.println("<body>");
				out.println("</body>");
				out.println("</html>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/selectOneSellcoupon/{code}", method = RequestMethod.GET)
	public void selectOneSellcoupon(@PathVariable String code,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			Sellcoupon u = new Sellcoupon();
			u.setCode(code);
			Sellcoupon s = (Sellcoupon) service.selectOneSellcoupon(u);
			super.setSuccess(s);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

	@RequestMapping(value = "/updateSellcouponStatus/{code}", method = RequestMethod.POST)
	public void updateSellcouponStatus(@PathVariable String code,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			Sellcoupon u = new Sellcoupon();
			String json = request.getParameter(StaticSources.JSONVO);
			if (json != null) {
				u = (Sellcoupon) JSONObject.toBean(JSONObject.fromObject(json), Sellcoupon.class);
			}
			u.setCode(code);
			u.setSatus("D__9a92yxowsy30");
			u.setExchangedate(SubNumUtil.getDate());
			super.setSuccess(service.updateSellcouponStatus(u));
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}
	@RequestMapping(value = "/checkpwd", method = RequestMethod.GET)
	public void checkpwd(HttpServletRequest request, HttpServletResponse response) {
		try {
			Sellcoupon u = new Sellcoupon();
			String json = request.getParameter(StaticSources.JSONVO);
			if (json != null) {
				u = (Sellcoupon) JSONObject.toBean(JSONObject.fromObject(json), Sellcoupon.class);
			}
			String pwd=MD5Util.string2MD5(u.getPwd());
			u.setPwd(pwd);
			Sellcoupon s = (Sellcoupon) service.checkPwd(u);
			super.setSuccess(s);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

}
