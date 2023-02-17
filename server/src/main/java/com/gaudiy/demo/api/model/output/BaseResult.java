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

//反映されるformat
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult implements Serializable {
  private static final long serialVersionUID = 1L;
  //errorMessageはisSuccessがfalseになる時に出る
  //dataはarrayかobject　APIによる
  private Boolean isSuccess;
  private String errorMessage;
  private Object data;
}