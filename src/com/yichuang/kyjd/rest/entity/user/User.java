package com.yichuang.kyjd.rest.entity.user;

import java.util.List;

import com.yichuang.kyjd.rest.entity.role.Role;


/**
* @author zj    default  
* 
* @version 1.1
*/
public class User implements java.io.Serializable{

  private static final long serialVersionUID=1L;

  Integer rowid;
  String user_code;
  String user_name;
  String password;
  String phone;
  String email;
  String vname;
  String tel;
  Object status;
  String sys_create_time;
  String sys_update_time;
  String sys_delete_time;
  Object sex;
  Object age;
  Object cardtype;
  String cardnum;
  String remarks;
  List<Role> listRole;
  String role_codes;

  /**
 * @return the role_codes
 */
public String getRole_codes() {
	return role_codes;
}

/**
 * @param role_codes the role_codes to set
 */
public void setRole_codes(String role_codes) {
	this.role_codes = role_codes;
}

/**
 * @return the listRole
 */
public List<Role> getListRole() {
	return listRole;
}

/**
 * @param listRole the listRole to set
 */
public void setListRole(List<Role> listRole) {
	this.listRole = listRole;
}

public Integer getRowid(){
      return rowid;
  }

  public void setRowid(Integer rowid) {
      this.rowid =rowid;
  }

  public String getUser_code(){
      return user_code;
  }

  public void setUser_code(String user_code) {
      this.user_code =user_code;
  }

  public String getUser_name(){
      return user_name;
  }

  public void setUser_name(String user_name) {
      this.user_name =user_name;
  }

  public String getPassword(){
      return password;
  }

  public void setPassword(String password) {
      this.password =password;
  }

  public String getPhone(){
      return phone;
  }

  public void setPhone(String phone) {
      this.phone =phone;
  }

  public String getEmail(){
      return email;
  }

  public void setEmail(String email) {
      this.email =email;
  }

  public String getVname(){
      return vname;
  }

  public void setVname(String vname) {
      this.vname =vname;
  }

  public String getTel(){
      return tel;
  }

  public void setTel(String tel) {
      this.tel =tel;
  }

  public Object getStatus(){
      return status;
  }

  public void setStatus(Object status) {
      this.status =status;
  }

  public String getSys_create_time(){
      return sys_create_time;
  }

  public void setSys_create_time(String sys_create_time) {
      this.sys_create_time =sys_create_time;
  }

  public String getSys_update_time(){
      return sys_update_time;
  }

  public void setSys_update_time(String sys_update_time) {
      this.sys_update_time =sys_update_time;
  }

  public String getSys_delete_time(){
      return sys_delete_time;
  }

  public void setSys_delete_time(String sys_delete_time) {
      this.sys_delete_time =sys_delete_time;
  }

  public Object getSex(){
      return sex;
  }

  public void setSex(Object sex) {
      this.sex =sex;
  }

  public Object getAge(){
      return age;
  }

  public void setAge(Object age) {
      this.age =age;
  }

  public Object getCardtype(){
      return cardtype;
  }

  public void setCardtype(Object cardtype) {
      this.cardtype =cardtype;
  }

  public String getCardnum(){
      return cardnum;
  }

  public void setCardnum(String cardnum) {
      this.cardnum =cardnum;
  }

  public String getRemarks(){
      return remarks;
  }

  public void setRemarks(String remarks) {
      this.remarks =remarks;
  }
  public User(){
  }
  public User(Integer rowid,String user_code,String user_name,String password,String phone,String email,String vname,String tel,Object status,String sys_create_time,String sys_update_time,String sys_delete_time,Object sex,Object age,Object cardtype,String cardnum,String remarks){
      this.rowid=rowid;
      this.user_code=user_code;
      this.user_name=user_name;
      this.password=password;
      this.phone=phone;
      this.email=email;
      this.vname=vname;
      this.tel=tel;
      this.status=status;
      this.sys_create_time=sys_create_time;
      this.sys_update_time=sys_update_time;
      this.sys_delete_time=sys_delete_time;
      this.sex=sex;
      this.age=age;
      this.cardtype=cardtype;
      this.cardnum=cardnum;
      this.remarks=remarks;
  }
}