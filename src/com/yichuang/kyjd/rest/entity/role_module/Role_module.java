package com.yichuang.kyjd.rest.entity.role_module;

/**
* @author zj    default  
* 
* @version 1.1
*/
public class Role_module implements java.io.Serializable{

  private static final long serialVersionUID=1L;

  Integer rowid;
  String role_code;
  String module_code;
  String remarks;

  public Integer getRowid(){
      return rowid;
  }

  public void setRowid(Integer rowid) {
      this.rowid =rowid;
  }

  public String getRole_code(){
      return role_code;
  }

  public void setRole_code(String role_code) {
      this.role_code =role_code;
  }

  public String getModule_code(){
      return module_code;
  }

  public void setModule_code(String module_code) {
      this.module_code =module_code;
  }

  public String getRemarks(){
      return remarks;
  }

  public void setRemarks(String remarks) {
      this.remarks =remarks;
  }
  public Role_module(){
  }
  public Role_module(Integer rowid,String role_code,String module_code,String remarks){
      this.rowid=rowid;
      this.role_code=role_code;
      this.module_code=module_code;
      this.remarks=remarks;
  }
}