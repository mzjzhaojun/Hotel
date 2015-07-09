package com.yichuang.kyjd.rest.entity.sellcoupon;

/**
 * @author zj default
 * 
 * @version 1.1
 */
public class Sellcoupon implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	Integer rowid;
	String name;
	String code;
	String pwd;
	float price;
	float buyprice;
	String issuedate;
	String duedate;
	String satus;
	String exchangedate;
	String buydate;
	String usercode;
	Integer buycount;
	String coupontype;
	String coupontype_name;
	String status_name;
	String remarks;
	
	
	
	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public String getCoupontype_name() {
		return coupontype_name;
	}

	public void setCoupontype_name(String coupontype_name) {
		this.coupontype_name = coupontype_name;
	}

	public Integer getRowid() {
		return rowid;
	}

	public void setRowid(Integer rowid) {
		this.rowid = rowid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getBuyprice() {
		return buyprice;
	}

	public void setBuyprice(float buyprice) {
		this.buyprice = buyprice;
	}

	public String getIssuedate() {
		return issuedate;
	}

	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	public String getSatus() {
		return satus;
	}

	public void setSatus(String satus) {
		this.satus = satus;
	}

	public String getExchangedate() {
		return exchangedate;
	}

	public void setExchangedate(String exchangedate) {
		this.exchangedate = exchangedate;
	}

	public String getBuydate() {
		return buydate;
	}

	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public Integer getBuycount() {
		return buycount;
	}

	public void setBuycount(Integer buycount) {
		this.buycount = buycount;
	}

	public String getCoupontype() {
		return coupontype;
	}

	public void setCoupontype(String coupontype) {
		this.coupontype = coupontype;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Sellcoupon() {
	}

	public Sellcoupon(Integer rowid, String name, String code, String pwd,
			float price, float buyprice, String issuedate, String duedate,
			String satus, String exchangedate, String buydate, String usercode,
			Integer buycount, String coupontype, String remarks) {
		this.rowid = rowid;
		this.name = name;
		this.code = code;
		this.pwd = pwd;
		this.price = price;
		this.buyprice = buyprice;
		this.issuedate = issuedate;
		this.duedate = duedate;
		this.satus = satus;
		this.exchangedate = exchangedate;
		this.buydate = buydate;
		this.usercode = usercode;
		this.buycount = buycount;
		this.coupontype = coupontype;
		this.remarks = remarks;
	}
}