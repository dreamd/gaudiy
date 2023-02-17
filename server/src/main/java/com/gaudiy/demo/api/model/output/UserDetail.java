package com.gaudiy.demo.api.model.output;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import com.gaudiy.demo.api.model.db.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail implements Serializable {
  private static final long serialVersionUID = 1L;

  public UserDetail(User user) {
      this.id = user.getId();
      this.email = user.getEmail();
      this.createTime = user.getCreateTime();
  }


  private long id;
  private String email;
  private Date createTime;
}