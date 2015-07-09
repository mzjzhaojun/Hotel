package com.yichuang.kyjd.rest.entity.restaurant;

/**
 * @author zj default
 * 
 * @version 1.1
 */
public class Restaurant implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	Integer rowid;
	String name;
	String number;
	String type;
	String type_name;
	Integer count;
	Integer surplus;
	String babychair;
	String iscoupon;
	String mincharge;
	Integer maxdinernum;
	String status;
	String status_name;
	String detail;
	String deposit;
	String lowest;
	String axf;
	String dningimg;
	String remark;

	public String getDningimg() {
		return dningimg;
	}

	public void setDningimg(String dningimg) {
		this.dningimg = dningimg;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getSurplus() {
		return surplus;
	}

	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}

	public String getLowest() {
		return lowest;
	}

	public void setLowest(String lowest) {
		this.lowest = lowest;
	}

	public String getAxf() {
		return axf;
	}

	public void setAxf(String axf) {
		this.axf = axf;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBabychair() {
		return babychair;
	}

	public void setBabychair(String babychair) {
		this.babychair = babychair;
	}

	public String getIscoupon() {
		return iscoupon;
	}

	public void setIscoupon(String iscoupon) {
		this.iscoupon = iscoupon;
	}

	public String getMincharge() {
		return mincharge;
	}

	public void setMincharge(String mincharge) {
		this.mincharge = mincharge;
	}

	public Integer getMaxdinernum() {
		return maxdinernum;
	}

	public void setMaxdinernum(Integer maxdinernum) {
		this.maxdinernum = maxdinernum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Restaurant() {
	}

	public Restaurant(Integer rowid, String name, String number, String type,
			String babychair, String iscoupon, String mincharge,
			Integer maxdinernum, String status, String detail, String deposit,
			String remark) {
		this.rowid = rowid;
		this.name = name;
		this.number = number;
		this.type = type;
		this.babychair = babychair;
		this.iscoupon = iscoupon;
		this.mincharge = mincharge;
		this.maxdinernum = maxdinernum;
		this.status = status;
		this.detail = detail;
		this.deposit = deposit;
		this.remark = remark;
	}
}