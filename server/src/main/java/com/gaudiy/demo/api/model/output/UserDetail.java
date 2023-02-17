package com.gaudiy.demo.api.model.output;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import com.gaudiy.demo.api.model.db.*;


//ユーザーのデータを反映されるformat
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail implements Serializable {
  private static final long serialVersionUID = 1L;

  public UserDetail(User user) {
      this.id = user.getId();
      this.coin = user.getCoin();
      this.createTime = user.getCreateTime();
  }

  //DBのユーザーと差があると思われるので別のmodelを作った
  private long id;
  private long coin;
  private Date createTime;
}