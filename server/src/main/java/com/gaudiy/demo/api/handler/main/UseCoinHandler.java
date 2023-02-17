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


//ユーザーIDと消費量を指定することによって、残高から独自コインを指定した分、減らすことができます。
@SuppressWarnings("unchecked")
@Component("UseCoinHandler")
public class UseCoinHandler {

  //使われるものをinitialする
  private static final Logger logger = LoggerFactory.getLogger(UseCoinHandler.class);
  private final JsonMapper jsonMapper;
  private final UserService userService;
  private final CoinHistoryService coinHistoryService;

  //使われるものを注入する
  @Autowired
  public UseCoinHandler(JsonMapper jsonMapper, UserService userService, CoinHistoryService coinHistoryService) {
    this.jsonMapper = jsonMapper;
    this.userService = userService;
    this.coinHistoryService = coinHistoryService;
  }

  //API実作　inputとoutputもjson
  //input必要　userId, coin
  //remarkはこのmirco serviceをつかうシステムでの検索用
  public String handle(Map<String, Object> req) throws JsonProcessingException {
    //req ログ
    logger.info("UseCoinHandler Req: " + jsonMapper.write(req));
    //baseResultという結構で反映される
    BaseResult res = new BaseResult();
    res.setIsSuccess(false);
    try {
      //userId、coin、remarkが正確かどうかチェックする
      Long userId = 0L;
      Long coin = 0L;
      String remark = "";
      if (req.get("userId") != null) {
        userId = NumberUtil.toLong(req.get("userId"));
      }
      if (req.get("coin") != null) {
        coin = NumberUtil.toLong(req.get("coin"));
      }
      if (req.get("remark") != null) {
        remark = (String)req.get("remark");
      }
      if (userId <= 0L) {
        throw new Exception("userId input error");
      }
      else if (coin <= 0L) {
        throw new Exception("coin input error");
      }
      else if (remark == null || remark.equals("")) {
        throw new Exception("remark input error");
      }
      //DBからユーザーデータを取得する
      User user = userService.dataById(userId);
      //取得したデータでcoinが足りるかチェックする
      if (user.getCoin() < coin) {
        throw new Exception("coin not enough");
      }
      //coinのhistoryを追加する
      CoinHistory coinHistory = coinHistoryService.addHistory(userId, coin * -1, remark);
      //ユーザーのデータをアップデートする
      user.setCoin(user.getCoin() - coin);
      userService.update(user);
      //取得したデータを反映する
      res.setIsSuccess(true);
      res.setData(new UserDetail(user));
    } catch (Exception e) {
      //エラーがあればmessageを反映する
      res.setErrorMessage(e.getMessage());
    }
    //json変換
    String response = jsonMapper.write(res);
    logger.info("UseCoinHandler Res: " + response);
    //res ログ
    return response;
  }
}