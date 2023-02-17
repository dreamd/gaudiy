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
import com.gaudiy.demo.api.util.NumberUtil;
import com.gaudiy.demo.api.service.*;
import com.gaudiy.demo.api.model.db.*;
import com.gaudiy.demo.api.model.output.*;

//ユーザーIDを指定することによって、今までどんな増加・消費がされたか日時付きで取得することができます。
@SuppressWarnings("unchecked")
@Component("HistoryHandler")
public class HistoryHandler {

  //使われるものをinitialする
  private static final Logger logger = LoggerFactory.getLogger(HistoryHandler.class);
  private final JsonMapper jsonMapper;
  private final UserService userService;
  private final CoinHistoryService coinHistoryService;

  //使われるものを注入する
  @Autowired
  public HistoryHandler(JsonMapper jsonMapper, UserService userService, CoinHistoryService coinHistoryService) {
    this.jsonMapper = jsonMapper;
    this.userService = userService;
    this.coinHistoryService = coinHistoryService;
  }

  //API実作　inputとoutputもjson
  //input必要　userId
  public String handle(Map<String, Object> req) throws JsonProcessingException {
    //req ログ
    logger.info("HistoryHandler Req: " + jsonMapper.write(req));
    BaseResult res = new BaseResult();
    res.setIsSuccess(false);
    try {
      //userIdが正確かどうかチェックする
      Long userId = 0L;
      if (req.get("userId") != null) {
        userId = NumberUtil.toLong(req.get("userId"));
      }
      if (userId <= 0L) {
        throw new Exception("userId input error");
      }
      //DBからユーザーデータを取得する
      User user = userService.dataById(userId);
      List<CoinHistory> coinHistoryList = coinHistoryService.listByUserId(userId);
      //取得したデータを反映する
      res.setIsSuccess(true);
      res.setData(coinHistoryList);
    } catch (Exception e) {
      //エラーがあればmessageを反映する
      res.setErrorMessage(e.getMessage());
    }
    //json変換
    String response = jsonMapper.write(res);
    logger.info("HistoryHandler Res: " + response);
    //res ログ
    return response;
  }
}