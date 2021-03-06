package com.yichuang.kyjd.rest.entity.customer;

/**
 * @author zj default
 * 
 * @version 1.1
 */
public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	Integer rowid;
	String nickname;
	String name;
	String code;
	String sex;
	String birthday;
	String status;
	String phone;
	String addr;
	String head_img;
	String integral;
	String rating;
	String sys_create_time;
	String accout;
	String password;
	String salt;
	String cardtype;
	String idcard;
	String remarks;

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRowid() {
		return rowid;
	}

	public void setRowid(Integer rowid) {
		this.rowid = rowid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getHead_img() {
		return head_img;
	}

	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSys_create_time() {
		return sys_create_time;
	}

	public void setSys_create_time(String sys_create_time) {
		this.sys_create_time = sys_create_time;
	}

	public String getAccout() {
		return accout;
	}

	public void setAccout(String accout) {
		this.accout = accout;
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Customer() {
	}

	public Customer(Integer rowid, String nickname, String name, String code,
			String sex, String birthday, String status, String phone,
			String addr, String head_img, String integral, String rating,
			String sys_create_time, String accout, String cardtype,
			String idcard, String remarks) {
		this.rowid = rowid;
		this.nickname = nickname;
		this.name = name;
		this.code = code;
		this.sex = sex;
		this.birthday = birthday;
		this.status = status;
		this.phone = phone;
		this.addr = addr;
		this.head_img = head_img;
		this.integral = integral;
		this.rating = rating;
		this.sys_create_time = sys_create_time;
		this.accout = accout;
		this.cardtype = cardtype;
		this.idcard = idcard;
		this.remarks = remarks;
	}
}