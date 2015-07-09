package com.yichuang.kyjd.commnd.webService;
 
import javax.jws.WebParam;
 
import javax.jws.WebService;
 
import javax.jws.soap.SOAPBinding;
 
import javax.jws.soap.SOAPBinding.Style;



/**
 * webservice 接口 
 * @author zj
 *
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface IService {
    public void getUserByName(@WebParam(name = "name") String name);
    public String getUser(String name,String password);
    
    public String invoke(String sg);
}

