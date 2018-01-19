package com.gdkyit.core.entity;

public class Token {
  private Long id;
  private String username;
  private String tokenname;
  private java.sql.Timestamp createtime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getTokenname() {
    return tokenname;
  }

  public void setTokenname(String tokenname) {
    this.tokenname = tokenname;
  }

  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }
}
