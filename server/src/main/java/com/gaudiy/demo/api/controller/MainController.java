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

@RestController
@RequestMapping(produces="application/json")
public class MainController {

  private final RegisterHandler registerHandler;

  @Autowired
  public MainController(RegisterHandler registerHandler) {
    this.registerHandler = registerHandler;
  }

  @PostMapping("/register")
  public String register(@RequestBody Map<String, Object> payload) throws JsonProcessingException {
    return registerHandler.handle(payload);
  }
}
