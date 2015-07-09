package com.yichuang.kyjd.rest.controller.user;

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
import com.yichuang.kyjd.commnd.web.StaticSources;
import com.yichuang.kyjd.rest.entity.role.Role;
import com.yichuang.kyjd.rest.entity.user.User;
import com.yichuang.kyjd.rest.entity.user_role.User_role;
import com.yichuang.kyjd.rest.service.role.impl.RoleServiceImpl;
import com.yichuang.kyjd.rest.service.user.impl.UserServiceImpl;
import com.yichuang.kyjd.rest.service.user_role.impl.User_roleServiceImpl;

/**
 * @author zj default test
 * 
 * @version 1.1
 */

@Controller
@RequestMapping("/rest/user")
public class UserController extends BaseController<User, Integer> {

	@Autowired
	private UserServiceImpl service;
	@Autowired
	private RoleServiceImpl serviceRole;

	@Autowired
	private User_roleServiceImpl serviceUserRole;

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
			User u = null;
			if (json != null) {
				u = (User) JSONObject.toBean(JSONObject.fromObject(json),
						User.class);
			}
			List<User> list = service.selectUser(u);
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
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(HttpServletRequest request, HttpServletResponse response) {
		try {
			String json = request.getParameter(StaticSources.JSONVO);
			User u = null;
			if (json != null) {
				u = (User) JSONObject.toBean(JSONObject.fromObject(new String(
						json.getBytes("ISO-8859-1"), "UTF-8")), User.class);
			}
			User user = service.getUser(u);
			// 正确
			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("token", user);
				super.setSuccess(user);
			} else {
				// 错误验证用户名称or密码
				if (service.getUserName(u) == null)
					super.setLoginUserError("noLoginName");
				else if (service.getUserPwd(u) == null)
					super.setLoginPwdError("noLoginPassword");
			}
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
	@RequestMapping(value = "/loginOut", method = RequestMethod.GET)
	public void loginOut(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("token");
			super.setSuccess("loginOut");
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

	/**
	 * custom /rest/user/checkusername
	 * 
	 * @version 1.1
	 */
	@RequestMapping(value = "/checkusername", method = RequestMethod.GET)
	public void checkUserName(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String json = request.getParameter(StaticSources.JSONVO);
			User u = null;
			if (json != null) {
				u = (User) JSONObject.toBean(JSONObject.fromObject(json),
						User.class);
			}
			super.setSuccess(service.getUserName(u));
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

	/**
	 * 根据id查询用户及角色信息(用于查询)
	 * 
	 * @version 1.1
	 */
	@RequestMapping(value = "/selectUserAndRole", method = RequestMethod.GET)
	public void selectUserAndRole(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String rowid = request.getParameter("rowid");
			User user = service.selectOne(rowid);// 根据id获取用户信息
			Role role = new Role();
			role.setUser_code(user.getUser_code());
			List<Role> listRole = serviceRole.getRoleByRoleCode(role);
			if (listRole != null && listRole.size() > 0) {
				user.setListRole(listRole);
			}

			super.setSuccess(user);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

	/**
	 * 根据id查询用户及角色信息(用于update)
	 * 
	 * @version 1.1
	 */
	@RequestMapping(value = "/selectUserAndRoleForUpdate", method = RequestMethod.GET)
	public void selectUserAndRoleForUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String rowid = request.getParameter("rowid");
			User user = service.selectOne(rowid);// 根据id获取用户信息
			Role role = new Role();
			role.setUser_code(user.getUser_code());
			List<Role> listRole = serviceRole.getRoleByRoleCode(role);
			List<Role> listRoleAll = serviceRole.getList(null);
			if (listRole != null && listRole.size() > 0 && listRoleAll != null)
				for (int i = 0; i < listRole.size(); i++) {
					for (int j = 0; j < listRoleAll.size(); j++) {
						if (listRole.get(i).getRole_code()
								.equals(listRoleAll.get(j).getRole_code())) {
							listRoleAll.get(j).setCheck("1");
						}
					}
				}

			if (listRoleAll != null && listRoleAll.size() > 0) {
				user.setListRole(listRoleAll);
			}

			super.setSuccess(user);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

	/**
	 * custom /rest/role/save
	 * 
	 * @version 1.1
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = null;
			User_role user_role = null;
			String json = request.getParameter(StaticSources.JSONVO);
			if (json != null) {
				user = (User) JSONObject.toBean(JSONObject.fromObject(json),
						User.class);
			}
			if (user != null) {
				if (user.getRole_codes() != null) {
					String[] rc = user.getRole_codes().split(",");
					for (int i = 0; i < rc.length; i++) {
						if (user.getUser_code() != null
								&& !user.getUser_code().equals("")
								&& rc[i] != null && !rc[i].equals("")) {
							user_role = new User_role();
							user_role.setUser_code(user.getUser_code());
							user_role.setRole_code(rc[i]);
							serviceUserRole.insert(user_role);
						}
						// super.setSuccess(roleModelservice.insert(role_module));
					}
				}
			}

			super.setSuccess(service.insert(user));

		} catch (Exception e) {
			super.setError(e.getMessage());
		}

		super.ResponseJson(response);

	}

	/**
	 * 修改 /rest/role/update
	 * 
	 * @version 1.1
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = null;
			User_role user_role = null;
			String json = request.getParameter(StaticSources.JSONVO);
			if (json != null) {
				user = (User) JSONObject.toBean(JSONObject.fromObject(json),
						User.class);
			}
			if (user != null) {
				if (user.getRole_codes() != null) {
					String[] rc = user.getRole_codes().split(",");
					serviceUserRole.deleteByUserCode(user.getUser_code());
					for (int i = 0; i < rc.length; i++) {
						if (user.getUser_code() != null
								&& !user.getUser_code().equals("")
								&& rc[i] != null && !rc[i].equals("")) {
							user_role = new User_role();
							user_role.setUser_code(user.getUser_code());
							user_role.setRole_code(rc[i]);
							serviceUserRole.insert(user_role);
						}
						// super.setSuccess(roleModelservice.insert(role_module));
					}
				}
			}

			super.setSuccess(service.update(user));

		} catch (Exception e) {
			super.setError(e.getMessage());
		}

		super.ResponseJson(response);

	}
}
