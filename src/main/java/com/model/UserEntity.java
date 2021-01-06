package com.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "USER")
public class UserEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "PORT")
   Integer port;

   @Column(name = "FULL_NAME", columnDefinition = "NVARCHAR(100) NOT NULL")
   String fullName;

   @Column(name = "EMAIL", columnDefinition = "VARCHAR(40) NOT NULL")
   String email;

   @Column(name = "CONTAINER_NAME", columnDefinition = "VARCHAR(40) NOT NULL")
   String containerName;

   public UserEntity() {
   }

   public UserEntity(String fullName, String email) {
      this.port = null;
      this.fullName = fullName;
      this.email = email;
      this.containerName = email.replace("@", "").replace(".","_").trim();
   }

   public Integer getPort() {
      return port;
   }

   public void setPort(Integer port) {
      this.port = port;
   }

   public String getFullName() {
      return fullName;
   }

   public void setFullName(String fullName) {
      this.fullName = fullName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getContainerName() {
      return containerName;
   }

   public void setContainerName(String containerName) {
      this.containerName = containerName;
   }
}
