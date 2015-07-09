package com.yichuang.kyjd.commnd.web;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.yichuang.kyjd.commnd.base.GetSqlCon;


/**
 * 
 * 
 * 
 * @author zj
 * 
 */
public class Main {

	public static GetSqlCon sqlcon = GetSqlCon.getInstance();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
//		insertData(10000);
		long endTime = System.currentTimeMillis();
		System.out.println("time：" + (endTime - startTime) / 1000 + "秒");
		
		
	}
	
	
	public static void insertData(int size) {
		try {
			Connection conn = sqlcon.getCon();

			conn.setAutoCommit(false);
			//insert into user(name, password) value (?, ?)
			//insert into scott(Name, age,sex) value (?, ?,?)
			//insert into tiger(Name, age,sex) value (?, ?,?)
			// 构造预处理statement
			PreparedStatement pst = conn
					.prepareStatement("insert into tiger(Name, age,sex) value (?, ?,?)");
			// 1万次循环
			for (int i = 1; i <= size; i++) {
				pst.setString(1, "name" + i);
				pst.setInt(2, i);
				pst.setInt(3, i);
				pst.addBatch();
				// 每1000次提交一次
				if (i % 1000 == 0) {// 可以设置不同的大小；如50，100，500，1000等等
					pst.executeBatch();
					conn.commit();
					pst.clearBatch();
				}
			}
			pst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
