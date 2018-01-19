package com.gdkyit.core.entity;

public class User {
  private Long id;
  private Long version;
  private String username;
  private String password;
  private Long account_enabled;
  private Long account_expired;
  private Long account_locked;
  private Long credentials_expired;
  private String names;
  private String phone;
  private java.sql.Timestamp create_time;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getAccount_enabled() {
    return account_enabled;
  }

  public void setAccount_enabled(Long account_enabled) {
    this.account_enabled = account_enabled;
  }

  public Long getAccount_expired() {
    return account_expired;
  }

  public void setAccount_expired(Long account_expired) {
    this.account_expired = account_expired;
  }

  public Long getAccount_locked() {
    return account_locked;
  }

  public void setAccount_locked(Long account_locked) {
    this.account_locked = account_locked;
  }

  public Long getCredentials_expired() {
    return credentials_expired;
  }

  public void setCredentials_expired(Long credentials_expired) {
    this.credentials_expired = credentials_expired;
  }

  public String getNames() {
    return names;
  }

  public void setNames(String names) {
    this.names = names;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public java.sql.Timestamp getCreate_time() {
    return create_time;
  }

  public void setCreate_time(java.sql.Timestamp create_time) {
    this.create_time = create_time;
  }
}
