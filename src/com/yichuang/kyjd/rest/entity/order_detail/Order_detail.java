package com.yichuang.kyjd.rest.entity.order_detail;

/**
* @author zj    default  
* 
* @version 1.1
*/
public class Order_detail implements java.io.Serializable{

  private static final long serialVersionUID=1L;

  Integer rowid;
  Integer order_rowid;
  String title;
  Integer count;
  float price;
  float new_price;
  Integer inperson;
  String name;
  String is_coupon;
  String remark;
  String indatetime;
  String outdatetime;
  String card;
  String subnum;
public Integer getRowid() {
	return rowid;
}
public void setRowid(Integer rowid) {
	this.rowid = rowid;
}
public Integer getOrder_rowid() {
	return order_rowid;
}
public void setOrder_rowid(Integer order_rowid) {
	this.order_rowid = order_rowid;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
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
public Integer getInperson() {
	return inperson;
}
public void setInperson(Integer inperson) {
	this.inperson = inperson;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getIs_coupon() {
	return is_coupon;
}
public void setIs_coupon(String is_coupon) {
	this.is_coupon = is_coupon;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public String getIndatetime() {
	return indatetime;
}
public void setIndatetime(String indatetime) {
	this.indatetime = indatetime;
}
public String getOutdatetime() {
	return outdatetime;
}
public void setOutdatetime(String outdatetime) {
	this.outdatetime = outdatetime;
}
public String getCard() {
	return card;
}
public void setCard(String card) {
	this.card = card;
}
public String getSubnum() {
	return subnum;
}
public void setSubnum(String subnum) {
	this.subnum = subnum;
}
public Order_detail() {
}
public Order_detail(Integer rowid, Integer order_rowid, String title,
		Integer count, float price, float new_price, Integer inperson,
		String name, String is_coupon, String remark, String indatetime,
		String outdatetime, String card, String subnum) {
	super();
	this.rowid = rowid;
	this.order_rowid = order_rowid;
	this.title = title;
	this.count = count;
	this.price = price;
	this.new_price = new_price;
	this.inperson = inperson;
	this.name = name;
	this.is_coupon = is_coupon;
	this.remark = remark;
	this.indatetime = indatetime;
	this.outdatetime = outdatetime;
	this.card = card;
	this.subnum = subnum;
}

 
}