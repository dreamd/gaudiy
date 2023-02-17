package com.gaudiy.demo.api.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//コインの記録のClassを設定する
@Entity(name = "CoinHistory")
@Table(name = "coin_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinHistory implements Serializable {

  private static final long serialVersionUID = 1L;

  public CoinHistory(long userId, long coin, String remark, Date createTime) {
    this.userId = userId;
    this.coin = coin;
    this.remark = remark;
    this.createTime = createTime;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  //必要なのはuserId, coin, createTime
  //remarkはこのmirco serviceをつかうシステムでの検索用
  private long id;
  private long userId;
  private long coin;
  private String remark;
  private Date createTime;
}
