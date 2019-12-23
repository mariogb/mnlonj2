/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnlonj2.pojo;

import io.micronaut.security.authentication.providers.UserState;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mario
 */
public class UserLon implements UserState {

  private Long id;
  
  @NotBlank
  @NotNull
  private String pname;

  @NotBlank
  @NotNull
  private String pkey;

  @NotBlank
  @NotNull
  private String username;

  @NotBlank
  @NotNull
  private String password;

  private boolean enabled = true;
  private boolean accountExpired = false;
  private boolean accountLocked = false;
  private boolean passwordExpired = false;

  public UserLon(String pname, String pkey, String username, String password) {
    this.pname = pname;
    this.pkey = pkey;
    this.username = username;
    this.password = password;
  }

  
  
  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * @return the pname
   */
  public String getPname() {
    return pname;
  }

  /**
   * @param pname the pname to set
   */
  public void setPname(String pname) {
    this.pname = pname;
  }

  /**
   * @return the pkey
   */
  public String getPkey() {
    return pkey;
  }

  /**
   * @param pkey the pkey to set
   */
  public void setPkey(String pkey) {
    this.pkey = pkey;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @param enabled the enabled to set
   */
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * @return the accountExpired
   */
  @Override
  public boolean isAccountExpired() {
    return accountExpired;
  }

  /**
   * @param accountExpired the accountExpired to set
   */
  public void setAccountExpired(boolean accountExpired) {
    this.accountExpired = accountExpired;
  }

  /**
   * @return the accountLocked
   */
  @Override
  public boolean isAccountLocked() {
    return accountLocked;
  }

  /**
   * @param accountLocked the accountLocked to set
   */
  public void setAccountLocked(boolean accountLocked) {
    this.accountLocked = accountLocked;
  }

  /**
   * @return the passwordExpired
   */
  @Override
  public boolean isPasswordExpired() {
    return passwordExpired;
  }

  /**
   * @param passwordExpired the passwordExpired to set
   */
  public void setPasswordExpired(boolean passwordExpired) {
    this.passwordExpired = passwordExpired;
  }

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

}
