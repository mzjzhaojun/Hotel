package com.yichuang.kyjd.rest.entity.news_info;

/**
* @author zj    default  
* 
* @version 1.1
*/
public class News_info implements java.io.Serializable{

  private static final long serialVersionUID=1L;

  Integer rowid;
  String type;
  String title;
  String subtitle;
  String context;
  String is_top;
  String is_top_name;
  public String getIs_top_name() {
	return is_top_name;
}

public void setIs_top_name(String is_top_name) {
	this.is_top_name = is_top_name;
}
String status;
  String is_talk;
  String modification_date;
  String add_date;
  String modification_user;
  String add_user;
  String remark;

  public Integer getRowid(){
      return rowid;
  }

  public void setRowid(Integer rowid) {
      this.rowid =rowid;
  }

  public String getType(){
      return type;
  }

  public void setType(String type) {
      this.type =type;
  }

  public String getTitle(){
      return title;
  }

  public void setTitle(String title) {
      this.title =title;
  }

  public String getSubtitle(){
      return subtitle;
  }

  public void setSubtitle(String subtitle) {
      this.subtitle =subtitle;
  }

  public String getContext(){
      return context;
  }

  public void setContext(String context) {
      this.context =context;
  }

  public String getIs_top(){
      return is_top;
  }

  public void setIs_top(String is_top) {
      this.is_top =is_top;
  }

  public String getStatus(){
      return status;
  }

  public void setStatus(String status) {
      this.status =status;
  }

  public String getIs_talk(){
      return is_talk;
  }

  public void setIs_talk(String is_talk) {
      this.is_talk =is_talk;
  }

  public String getModification_date(){
      return modification_date;
  }

  public void setModification_date(String modification_date) {
      this.modification_date =modification_date;
  }

  public String getAdd_date(){
      return add_date;
  }

  public void setAdd_date(String add_date) {
      this.add_date =add_date;
  }

  public String getModification_user(){
      return modification_user;
  }

  public void setModification_user(String modification_user) {
      this.modification_user =modification_user;
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
  public News_info(){
  }
  public News_info(Integer rowid,String type,String title,String subtitle,String context,String is_top,String status,String is_talk,String modification_date,String add_date,String modification_user,String add_user,String remark){
      this.rowid=rowid;
      this.type=type;
      this.title=title;
      this.subtitle=subtitle;
      this.context=context;
      this.is_top=is_top;
      this.status=status;
      this.is_talk=is_talk;
      this.modification_date=modification_date;
      this.add_date=add_date;
      this.modification_user=modification_user;
      this.add_user=add_user;
      this.remark=remark;
  }
}