package com.yichuang.kyjd.commnd.webService.impl;
 
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


import com.yichuang.kyjd.commnd.webService.IService;


/**
 * webService 公开方法类
 * 
 * @author zj
 *
 */
@WebService
@SOAPBinding(style = Style.RPC)
public class WsdlService implements IService {
	
//	private UserServiceImpl service;
	
    public void getUserByName(@WebParam(name = "name") String name) {
    }
    
    public String getUser(String name,String passwrod) {
    	System.out.println("setUser:" + name);
       
        return name+"返回报文！！！"+name;
    }

	@Override
	public String invoke(String sg) {
		// TODO Auto-generated method stub
		return null;
	}
}

