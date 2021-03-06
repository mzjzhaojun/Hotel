package com.yichuang.kyjd.rest.controller.room;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.yichuang.kyjd.commnd.base.impl.BaseController;
import com.yichuang.kyjd.commnd.system.util.SubNumUtil;
import com.yichuang.kyjd.commnd.web.StaticSources;
import net.sf.json.JSONObject;
import com.yichuang.kyjd.rest.service.room.impl.RoomServiceImpl;
import com.yichuang.kyjd.rest.entity.room.Room;

/**
 * @author zj default test
 * 
 * @version 1.1
 */

@Controller
@RequestMapping("/rest/room")
public class RoomController extends BaseController<Room, Integer> {

	@Autowired
	private RoomServiceImpl service;

	@Autowired
	public void setBaseService() {
		setBaseService(service);
	}

	/**
	 * custom
	 * 
	 * @version 1.1
	 */
	@RequestMapping(value = "/checksurplus", method = RequestMethod.GET)
	public void customSelect(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String json = request.getParameter(StaticSources.JSONVO);
			Room u = null;
			if (json != null) {
				u = (Room) JSONObject.toBean(JSONObject.fromObject(json),
						Room.class);
			}
			Object obj = service.selectRoomSurplus(u.getRowid().toString());
			super.setSuccess(obj);
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
	@RequestMapping(value = "/mobile", method = RequestMethod.GET)
	public void mobile(HttpServletRequest request, HttpServletResponse response) {
		try {
			String json = request.getParameter(StaticSources.JSONVO);
			Room u = null;
			if (json != null) {
				u = (Room) JSONObject.toBean(JSONObject.fromObject(json),
						Room.class);
			}
			List<Room> list = service.selectMobileRoom(u);
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
	@RequestMapping(value = "/selectDate", method = RequestMethod.GET)
	public void selectDate(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String json = request.getParameter(StaticSources.JSONVO);
			Room u = null;
			if (json != null) {
				u = (Room) JSONObject.toBean(JSONObject.fromObject(json),
						Room.class);
			}
			List<String> list = service.getDates(u.getSdatetime(),
					u.getEdatetime());
			super.setSuccess(list);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void upload(HttpServletRequest request, HttpServletResponse response) {
		try {
			// List<Room> list = service.selectMobileRoom(u);
			request.setCharacterEncoding("utf-8"); // 设置编码
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");
			// 获得磁盘文件条目工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 如果没以下两行设置的话，上传大的文件会占用很多内存，
			// 设置暂时存放的存储室 ,这个存储室,可以和最终存储文件的目录不同
			/**
			 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以
			 * .tmp 格式的 然后再将其真正写到 对应目录的硬盘上
			 */
			// factory.setRepository(new File("test"));
			// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
			// factory.setSizeThreshold(1024 * 1024);

			// 高水平的API文件上传处理
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 提交上来的信息都在这个list里面
			// 这意味着可以上传多个文件
			// 请自行组织代码
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);
			String fileName = "";
			List<String> fileNames = new ArrayList<String>();
			String uploadPath = request.getSession().getServletContext()
					.getRealPath("")
					+ "_web/img/upload";
			// 获取上传的文件
			for (int i = 0; i < list.size(); i++) {
				FileItem item = list.get(i);
				// 获取文件名
				fileName = item.getName();
				fileName = SubNumUtil.getSubNumCode()
						+ fileName.substring(fileName.lastIndexOf("."),
								fileName.length());
				if (fileName != null) {
					File fullFile = new File(fileName);
					// 要存入文件夹的路径
					File savedFile = new File(uploadPath, fullFile.getName());
					fileNames.add(fileName);
					item.write(savedFile);
				}
			}
			super.setSuccess(fileNames);
		} catch (Exception e) {
			super.setError(e.getMessage());
		}
		super.ResponseJson(response);
	}
}
