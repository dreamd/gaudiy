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

@SuppressWarnings("unchecked")
@Component("RegisterHandler")
public class RegisterHandler {

  private static final Logger logger = LoggerFactory.getLogger(RegisterHandler.class);
  private final JsonMapper jsonMapper;

  private final UserService userService;

  @Autowired
  public RegisterHandler(JsonMapper jsonMapper, UserService userService) {
    this.jsonMapper = jsonMapper;
    this.userService = userService;
  }


  public String handle(Map<String, Object> req) throws JsonProcessingException {
    logger.info("RegisterHandler Req: " + jsonMapper.write(req));
    UserResult res = new UserResult();
    res.setIsSuccess(false);
    try {
      String email = "";
      if (req.get("email") != null) {
        email = req.get("email").toString();
      }
      logger.info("RegisterHandler email: " + email);  
      if (email == null || email.equals("")) {
        throw new Exception("email input error");
      }
      User user = userService.register(email);
      res.setIsSuccess(true);
      res.setData(new UserDetail(user));
    } catch (Exception e) {
      res.setErrorMessage(e.getMessage());
    }
    String response = jsonMapper.write(res);
    logger.info("RegisterHandler Res: " + response);
    return response;
  }
}