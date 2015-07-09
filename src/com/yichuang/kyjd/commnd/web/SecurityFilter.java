package com.yichuang.kyjd.commnd.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yichuang.kyjd.rest.entity.customer.Customer;
import com.yichuang.kyjd.rest.entity.user.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SecurityFilter extends JsonResponse implements Filter {

	private String encoding;
	private String filterUrl;

	public void destroy() {
	}

	// 拦截器
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		req.setCharacterEncoding(encoding);
		res.setCharacterEncoding(encoding);
		if (!isAllowUrl(req.getRequestURI().toString())) {
			HttpSession session = req.getSession();
			User token = (User) session.getAttribute("token");
			if (token == null) {
				Customer cust = (Customer) session.getAttribute("cust");
				if (cust == null) {
					super.setNoLogin("noLogin!");
					super.ResponseJson(res);
				} else {
					chain.doFilter(request, response);
				}
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
		// chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		this.encoding = config.getInitParameter("Encoding");
		this.filterUrl = config.getInitParameter("allowUrl");
	}

	private boolean isAllowUrl(String reqUrl) {
		boolean isAllowUrl = true;
		try {
			JSONObject jsonUrl = JSONObject.fromObject(this.filterUrl);
			JSONArray jsonAllowUrls = JSONArray
					.fromObject(jsonUrl.get("allow"));
			JSONArray jsonFilterUrls = JSONArray.fromObject(jsonUrl
					.get("filter"));
			if (jsonAllowUrls != null && jsonAllowUrls.size() > 0) {
				boolean flag = false;
				for (int i = 0; i < jsonAllowUrls.size(); i++) {
					if (isMatchUrl(reqUrl, jsonAllowUrls.get(i).toString())) {
						return true;
					}
				}
				if (jsonFilterUrls != null && jsonFilterUrls.size() > 0) {
					for (int i = 0; i < jsonFilterUrls.size(); i++) {
						if (isMatchUrl(reqUrl, jsonFilterUrls.get(i).toString())) {
							flag = true;
							break;
						}
					}
					return !flag;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAllowUrl;
	}

	private boolean isMatchUrl(String reqUrl, String matchUrl) {

		if (matchUrl.charAt(0) == '/' && matchUrl.indexOf("*") == -1
				&& reqUrl.equals(matchUrl)) {
			return true;
		}
		if (matchUrl.charAt(0) == '/'
				&& matchUrl.indexOf("*") != -1
				&& reqUrl.indexOf(matchUrl.substring(0, matchUrl.indexOf("*"))) != -1) {
			return true;
		}
		if (matchUrl.charAt(0) == '*'
				&& reqUrl
						.indexOf(matchUrl.substring(matchUrl.indexOf("*") + 1)) != -1) {
			return true;
		}
		return false;
	}

}
