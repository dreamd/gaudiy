package com.gaudiy.demo.api.model.output;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResult implements Serializable {
  private static final long serialVersionUID = 1L;
  private Boolean isSuccess;
  private String errorMessage;
  private UserDetail data;
}