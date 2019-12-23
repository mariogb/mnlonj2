/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnlonj2.auth;

import io.micronaut.security.authentication.UserDetails;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author mario
 */
public class UserLonDetails extends UserDetails {

  private String pname;
  private String pkey;
  private Long id;
  Collection<Long> departaments;
  String typeLon;
  Collection<Long> bases;

  public UserLonDetails(String pname,String pkey,Long id,String username, Collection<String> roles, Map<String, Object> map) {
    super(username, roles, map);
//    this.departaments = departaments;
    this.pname = pname;
    this.id = id;
    this.pkey = pkey;
//    this.typeLon = typeLon;
//    this.bases = bases;
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
