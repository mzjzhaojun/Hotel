package com.yichuang.kyjd.rest.entity.specialty_info;

/**
* @author zj    default  
* 
* @version 1.1
*/
public class Specialty_info implements java.io.Serializable{

  private static final long serialVersionUID=1L;

  Integer rowid;
  String title;
  String name;
  String type;
  Integer count;
  float price;
  float new_price;
  String image_url;
  String context;
  Integer higth_count;
  String is_top;
  String is_top_name;
  String is_coupon;
  Integer higth_coupon_count;
  String higth_coupon_price;
  String is_add;
  String is_add_name;
  String status;
  String starus_name;
  /**
 * @return the is_top_name
 */
public String getIs_top_name() {
	return is_top_name;
}


/**
 * @param is_top_name the is_top_name to set
 */
public void setIs_top_name(String is_top_name) {
	this.is_top_name = is_top_name;
}


/**
 * @return the is_add_name
 */
public String getIs_add_name() {
	return is_add_name;
}


/**
 * @param is_add_name the is_add_name to set
 */
public void setIs_add_name(String is_add_name) {
	this.is_add_name = is_add_name;
}


/**
 * @return the starus_name
 */
public String getStarus_name() {
	return starus_name;
}


/**
 * @param starus_name the starus_name to set
 */
public void setStarus_name(String starus_name) {
	this.starus_name = starus_name;
}


String modification_date;
  String add_date;
  String modification_user;
  String add_user;
  String remark;

 
  public Specialty_info(){
  }


public Specialty_info(Integer rowid, String title, String name, String type,
		Integer count, float price, float new_price, String image_url,
		String context, Integer higth_count, String is_top, String is_coupon,
		Integer higth_coupon_count, String higth_coupon_price, String is_add,
		String status, String modification_date, String add_date,
		String modification_user, String add_user, String remark) {
	super();
	this.rowid = rowid;
	this.title = title;
	this.name = name;
	this.type = type;
	this.count = count;
	this.price = price;
	this.new_price = new_price;
	this.image_url = image_url;
	this.context = context;
	this.higth_count = higth_count;
	this.is_top = is_top;
	this.is_coupon = is_coupon;
	this.higth_coupon_count = higth_coupon_count;
	this.higth_coupon_price = higth_coupon_price;
	this.is_add = is_add;
	this.status = status;
	this.modification_date = modification_date;
	this.add_date = add_date;
	this.modification_user = modification_user;
	this.add_user = add_user;
	this.remark = remark;
}


public Integer getRowid() {
	return rowid;
}


public void setRowid(Integer rowid) {
	this.rowid = rowid;
}


public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getType() {
	return type;
}


public void setType(String type) {
	this.type = type;
}


public Integer getCount() {
	return count;
}


public void setCount(Integer count) {
	this.count = count;
}


public float getPrice() {
	return price;
}


public void setPrice(float price) {
	this.price = price;
}


public float getNew_price() {
	return new_price;
}


public void setNew_price(float new_price) {
	this.new_price = new_price;
}


public String getImage_url() {
	return image_url;
}


public void setImage_url(String image_url) {
	this.image_url = image_url;
}


public String getContext() {
	return context;
}


public void setContext(String context) {
	this.context = context;
}


public Integer getHigth_count() {
	return higth_count;
}


public void setHigth_count(Integer higth_count) {
	this.higth_count = higth_count;
}


public String getIs_top() {
	return is_top;
}


public void setIs_top(String is_top) {
	this.is_top = is_top;
}


public String getIs_coupon() {
	return is_coupon;
}


public void setIs_coupon(String is_coupon) {
	this.is_coupon = is_coupon;
}


public Integer getHigth_coupon_count() {
	return higth_coupon_count;
}


public void setHigth_coupon_count(Integer higth_coupon_count) {
	this.higth_coupon_count = higth_coupon_count;
}


public String getHigth_coupon_price() {
	return higth_coupon_price;
}


public void setHigth_coupon_price(String higth_coupon_price) {
	this.higth_coupon_price = higth_coupon_price;
}


public String getIs_add() {
	return is_add;
}


public void setIs_add(String is_add) {
	this.is_add = is_add;
}


public String getStatus() {
	return status;
}


public void setStatus(String status) {
	this.status = status;
}


public String getModification_date() {
	return modification_date;
}


public void setModification_date(String modification_date) {
	this.modification_date = modification_date;
}


public String getAdd_date() {
	return add_date;
}


public void setAdd_date(String add_date) {
	this.add_date = add_date;
}


public String getModification_user() {
	return modification_user;
}


public void setModification_user(String modification_user) {
	this.modification_user = modification_user;
}


public String getAdd_user() {
	return add_user;
}


public void setAdd_user(String add_user) {
	this.add_user = add_user;
}


public String getRemark() {
	return remark;
}


public void setRemark(String remark) {
	this.remark = remark;
}
 
}