package com.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SERVER_INFO")
public class ServerInfoEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   Integer id;
   @Column(name = "HOST")
   String host;
   @Column(name = "ADMIN_USERNAME")
   String adminUserName;
   @Column(name = "ADMIN_PASSWORD")
   String adminPassword;
   @Column(name = "ADMIN_PORT")
   Integer adminPort;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getHost() {
      return host;
   }

   public void setHost(String host) {
      this.host = host;
   }

   public String getAdminUserName() {
      return adminUserName;
   }

   public void setAdminUserName(String adminUserName) {
      this.adminUserName = adminUserName;
   }

   public String getAdminPassword() {
      return adminPassword;
   }

   public void setAdminPassword(String adminPassword) {
      this.adminPassword = adminPassword;
   }

   public Integer getAdminPort() {
      return adminPort;
   }

   public void setAdminPort(Integer adminPort) {
      this.adminPort = adminPort;
   }
}
