package com.yichuang.kyjd.commnd.base;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * 数据库连接
 * 
 * 
 * @author zjma
 *
 */
public class GetSqlCon {
	private static GetSqlCon getcon;
	private GetSqlCon(){
		init();
	}
	public static GetSqlCon getInstance(){
		if(getcon==null){
			getcon=new GetSqlCon();
		}
		return getcon;
	}
	private static String drivers;
	private static String url;
	private static String username;
	private static String password;
	private static String dbType;
	
	private static String FILE_PATH_NAME="jdbc.properties";
	private void init(){
	   try{
		    InputStream in = GetSqlCon.class.getClassLoader().getResourceAsStream(FILE_PATH_NAME);
		    Properties props = new Properties();
	
		    props.load(in);
		    in.close();
		    drivers = props.getProperty("jdbc.driverClassName");
		    url = props.getProperty("jdbc.url");
		    username = props.getProperty("jdbc.username");
		    password = props.getProperty("jdbc.password");
		    dbType = props.getProperty("jdbc.type");
	   }catch(IOException e){
	    e.printStackTrace();
	   }           
	}


	public Connection getCon(){         
		if(drivers==null||drivers.equals("")){
			init();
		}
		   Connection conn = null;
		   try{
			    Class.forName(drivers);
			    conn = DriverManager.getConnection(url, username, password);
		   }catch (SQLException e){
		    e.printStackTrace();
		   }catch (ClassNotFoundException e){
		    e.printStackTrace();
		   }
		   return conn;
	
	}
	
	public String getType(){         
		   return dbType;
	}
}
