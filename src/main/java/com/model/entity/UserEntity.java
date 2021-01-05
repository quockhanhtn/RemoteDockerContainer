package com.model.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "USER")
public class UserEntity implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "PORT")
   Long port;

   @Column(name = "FULLNAME", columnDefinition = "NVARCHAR(100) NOT NULL")
   String fullName;

   @Column(name = "EMAIL", columnDefinition = "VARCHAR(40) NOT NULL")
   String email;

   public UserEntity() {
   }

   public UserEntity(Long port, String fullName, String email) {
      this.port = port;
      this.fullName = fullName;
      this.email = email;
   }

   public Long getPort() {return port;}

   public void setPort(Long port) {this.port = port;}

   public String getFullName() {return fullName; }

   public void setFullName(String fullName) {this.fullName = fullName;}


   public String getEmail() {return email;}

   public void setEmail(String email) {this.email = email;}
}
