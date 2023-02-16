package com.gaudiy.demo.api.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "User")
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  public User(String email, Date datetime) {
    this.email = email;
    this.datetime = datetime;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String email;
  private Date datetime;
}
