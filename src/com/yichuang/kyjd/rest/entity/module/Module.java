package com.yichuang.kyjd.rest.entity.module;

/**
* @author zj    default  
* 
* @version 1.1
*/
public class Module implements java.io.Serializable{

  private static final long serialVersionUID=1L;

  Integer rowid;
  String module_code;
  String module_name;
  String module_desc;
  String parent_module_code;
  String url;
  Integer level;
  String icon;
  Object descs;
  String remarks;
  String ischecked;
  

 

/**
 * @return the ischecked
 */
public String getIschecked() {
	return ischecked;
}

/**
 * @param ischecked the ischecked to set
 */
public void setIschecked(String ischecked) {
	this.ischecked = ischecked;
}

public Integer getRowid(){
      return rowid;
  }

  public void setRowid(Integer rowid) {
      this.rowid =rowid;
  }

  public String getModule_code(){
      return module_code;
  }

  public void setModule_code(String module_code) {
      this.module_code =module_code;
  }

  public String getModule_name(){
      return module_name;
  }

  public void setModule_name(String module_name) {
      this.module_name =module_name;
  }

  public String getModule_desc(){
      return module_desc;
  }

  public void setModule_desc(String module_desc) {
      this.module_desc =module_desc;
  }

  public String getParent_module_code(){
      return parent_module_code;
  }

  public void setParent_module_code(String parent_module_code) {
      this.parent_module_code =parent_module_code;
  }

  public String getUrl(){
      return url;
  }

  public void setUrl(String url) {
      this.url =url;
  }

  public Integer getLevel(){
      return level;
  }

  public void setLevel(Integer level) {
      this.level =level;
  }

  public String getIcon(){
      return icon;
  }

  public void setIcon(String icon) {
      this.icon =icon;
  }

  public Object getDescs(){
      return descs;
  }

  public void setDescs(Object descs) {
      this.descs =descs;
  }

  public String getRemarks(){
      return remarks;
  }

  public void setRemarks(String remarks) {
      this.remarks =remarks;
  }
  public Module(){
  }
  public Module(Integer rowid,String module_code,String module_name,String module_desc,String parent_module_code,String url,Integer level,String icon,Object descs,String remarks){
      this.rowid=rowid;
      this.module_code=module_code;
      this.module_name=module_name;
      this.module_desc=module_desc;
      this.parent_module_code=parent_module_code;
      this.url=url;
      this.level=level;
      this.icon=icon;
      this.descs=descs;
      this.remarks=remarks;
  }
}