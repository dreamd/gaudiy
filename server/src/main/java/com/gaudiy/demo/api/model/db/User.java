package com.gaudiy.demo.api.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//ユーザーのClassを設定する
@Entity(name = "User")
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  public User(String email, long coin, Date createTime) {
    this.email = email;
    this.coin = coin;
    this.createTime = createTime;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  //必要なのはid, coin
  //emailはこのmirco serviceをつかうシステムからのkey
  private long id;
  private String email;
  private long coin;
  private Date createTime;
}
