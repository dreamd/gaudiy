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

@SuppressWarnings("unchecked")
@Component("RegisterHandler")
public class RegisterHandler {

  private static final Logger logger = LoggerFactory.getLogger(RegisterHandler.class);


  public String handle() {
    logger.info("RegisterHandler");
    return "1";
  }
}