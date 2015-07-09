package com.yichuang.kyjd.commnd.web;

import java.util.List;

public class Data {
	private static final long serialVersionUID=1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	String name;
	String methodName;
	String url;
	String method;
	List<Data> list;

	public List<Data> getList() {
		return list;
	}
	public void setList(List<Data> list) {
		this.list = list;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Data(String name, String methodName, String url, String method) {
		super();
		this.name = name;
		this.methodName = methodName;
		this.url = url;
		this.method = method;
	}
	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
