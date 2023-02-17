package com.gaudiy.demo.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gaudiy.demo.api.handler.main.*;

import java.util.Map;

//exampleなので今回Controllerのpathは使わないままでmirco serviceとして開発する
@RestController
@RequestMapping(produces="application/json")
public class MainController {

  //要求に応じて以下6つのAPIを開発した
  private final UserDataHandler userDataHandler;
  private final RegisterHandler registerHandler;
  private final AddCoinHandler addCoinHandler;
  private final UseCoinHandler useCoinHandler;
  private final SendCoinHandler sendCoinHandler;
  private final HistoryHandler historyHandler;


  //static使わないので、class variableで用いる
  @Autowired
  public MainController(
    UserDataHandler userDataHandler,
    RegisterHandler registerHandler,
    AddCoinHandler addCoinHandler,
    UseCoinHandler useCoinHandler,
    SendCoinHandler sendCoinHandler,
    HistoryHandler historyHandler
  ) {
    this.userDataHandler = userDataHandler;
    this.registerHandler = registerHandler;
    this.addCoinHandler = addCoinHandler;
    this.useCoinHandler = useCoinHandler;
    this.sendCoinHandler = sendCoinHandler;
    this.historyHandler = historyHandler;
  }

  //ユーザーIDを指定することによって、ユーザーの残高を確認することができます。
  @PostMapping("/userData")
  public String userData(@RequestBody Map<String, Object> payload) throws JsonProcessingException {
    return userDataHandler.handle(payload);
  }

  //事前にユーザー情報を登録することによって、そのユーザーは独自コインを管理できるようになります。
  @PostMapping("/register")
  public String register(@RequestBody Map<String, Object> payload) throws JsonProcessingException {
    return registerHandler.handle(payload);
  }

  //ユーザーIDと増加量を指定することによって、残高に独自コインを追加することができます。
  @PostMapping("/addCoin")
  public String addCoin(@RequestBody Map<String, Object> payload) throws JsonProcessingException {
    return addCoinHandler.handle(payload);
  }

  //ユーザーIDと消費量を指定することによって、残高から独自コインを指定した分、減らすことができます。
  @PostMapping("/useCoin")
  public String useCoin(@RequestBody Map<String, Object> payload) throws JsonProcessingException {
    return useCoinHandler.handle(payload);
  }

  //Aさん → Bさんへの独自コインの送金も行うことができます。
  @PostMapping("/sendCoin")
  public String sendCoin(@RequestBody Map<String, Object> payload) throws JsonProcessingException {
    return sendCoinHandler.handle(payload);
  }

  //ユーザーIDを指定することによって、今までどんな増加・消費がされたか日時付きで取得することができます。
  @PostMapping("/history")
  public String history(@RequestBody Map<String, Object> payload) throws JsonProcessingException {
    return historyHandler.handle(payload);
  }
}
