package com.gaudiy.demo.api.handler.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import com.gaudiy.demo.api.util.JsonMapper;
import com.gaudiy.demo.api.service.*;
import com.gaudiy.demo.api.model.db.*;
import com.gaudiy.demo.api.model.output.*;


//事前にユーザー情報を登録することによって、そのユーザーは独自コインを管理できるようになります。
@SuppressWarnings("unchecked")
@Component("RegisterHandler")
public class RegisterHandler {

  //使われるものをinitialする
  private static final Logger logger = LoggerFactory.getLogger(RegisterHandler.class);
  private final JsonMapper jsonMapper;
  private final UserService userService;

  //使われるものを注入する
  @Autowired
  public RegisterHandler(JsonMapper jsonMapper, UserService userService) {
    this.jsonMapper = jsonMapper;
    this.userService = userService;
  }

  //API実作　inputとoutputもjson
  //emailはこのmirco serviceをつかうシステムからのKEY
  public String handle(Map<String, Object> req) throws JsonProcessingException {
    //req ログ
    logger.info("RegisterHandler Req: " + jsonMapper.write(req));
    BaseResult res = new BaseResult();
    res.setIsSuccess(false);
    try {
      //emailが正確かどうかチェックする
      String email = "";
      if (req.get("email") != null) {
        email = ((String)req.get("email")).trim().toLowerCase();
      }
      if (email == null || email.equals("")) {
        throw new Exception("email input error");
      }
      //ユーザー情報を登録する
      User user = userService.register(email);
      //取得したデータを反映する
      res.setIsSuccess(true);
      res.setData(new UserDetail(user));
    } catch (Exception e) {
      //エラーがあればmessageを反映する
      res.setErrorMessage(e.getMessage());
    }
    //json変換
    String response = jsonMapper.write(res);
    logger.info("RegisterHandler Res: " + response);
    //res ログ
    return response;
  }
}