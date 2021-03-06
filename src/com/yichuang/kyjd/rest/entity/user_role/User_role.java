package com.yichuang.kyjd.rest.entity.user_role;

/**
* @author zj    default  
* 
* @version 1.1
*/
public class User_role implements java.io.Serializable{

  private static final long serialVersionUID=1L;

  Integer rowid;
  String user_code;
  String role_code;
  String remarks;

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

  public String getRole_code(){
      return role_code;
  }

  public void setRole_code(String role_code) {
      this.role_code =role_code;
  }

  public String getRemarks(){
      return remarks;
  }

  public void setRemarks(String remarks) {
      this.remarks =remarks;
  }
  public User_role(){
  }
  public User_role(Integer rowid,String user_code,String role_code,String remarks){
      this.rowid=rowid;
      this.user_code=user_code;
      this.role_code=role_code;
      this.remarks=remarks;
  }
}