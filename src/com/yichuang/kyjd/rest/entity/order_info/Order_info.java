package com.yichuang.kyjd.rest.entity.order_info;


import com.yichuang.kyjd.rest.entity.order_detail.Order_detail;

/**
* @author zj    default  
* 
* @version 1.1
*/
public class Order_info implements java.io.Serializable{

  private static final long serialVersionUID=1L;

  Integer rowid;
  String type;
  String no;
  String status;
  String statusname;
  float sumamounts;
  String add_date;
  String add_user;
  String mobile_phone;
  String remark;
  String title;
  String subnum;
  String address;
  public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

/**
 * @return the subnum
 */
public String getSubnum() {
	return subnum;
}

/**
 * @param subnum the subnum to set
 */
public void setSubnum(String subnum) {
	this.subnum = subnum;
}

/**
 * @return the title
 */
public String getTitle() {
	return title;
}

/**
 * @param title the title to set
 */
public void setTitle(String title) {
	this.title = title;
}
Order_detail orderdetail;





/**
 * @return the statusname
 */
public String getStatusname() {
	return statusname;
}

/**
 * @param statusname the statusname to set
 */
public void setStatusname(String statusname) {
	this.statusname = statusname;
}

public Order_detail getOrderdetail() {
	return orderdetail;
}

public void setOrderdetail(Order_detail orderdetail) {
	this.orderdetail = orderdetail;
}

public String getMobile_phone() {
	return mobile_phone;
}

public void setMobile_phone(String mobile_phone) {
	this.mobile_phone = mobile_phone;
}

public Integer getRowid(){
      return rowid;
  }

  public void setRowid(Integer rowid) {
      this.rowid =rowid;
  }


  public String getNo(){
      return no;
  }

  public void setNo(String no) {
      this.no =no;
  }

  public String getStatus(){
      return status;
  }

  public void setStatus(String status) {
      this.status =status;
  }



  public float getSumamounts(){
      return sumamounts;
  }

  public void setSumamounts(float sumamounts) {
      this.sumamounts =sumamounts;
  }

  public String getAdd_date(){
      return add_date;
  }

  public void setAdd_date(String add_date) {
      this.add_date =add_date;
  }

  public String getAdd_user(){
      return add_user;
  }

  public void setAdd_user(String add_user) {
      this.add_user =add_user;
  }

  public String getRemark(){
      return remark;
  }

  public void setRemark(String remark) {
      this.remark =remark;
  }
  
  public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public Order_info(){
  }
  public Order_info(Integer rowid,String type,String no,String status,float sumamounts,String add_date,String add_user,String mobile_phone,String remark){
      this.rowid=rowid;
      this.type=type;
      this.no=no;
      this.status=status;
      this.sumamounts=sumamounts;
      this.add_date=add_date;
      this.add_user=add_user;
      this.remark=remark;
      this.mobile_phone=mobile_phone;
  }
}