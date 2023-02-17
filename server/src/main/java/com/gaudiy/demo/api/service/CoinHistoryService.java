package com.gaudiy.demo.api.service;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CompletableFuture;
import java.util.Optional;
import com.gaudiy.demo.api.repo.*;
import com.gaudiy.demo.api.model.db.*;

//コイン記録DBを操作するclass
@Service
public class CoinHistoryService {

  //使われるものをinitialする
  private static CoinHistoryRepository coinHistoryRepository;

  //使われるものを注入する
  @Autowired
  public void setCoinHistoryRepository(CoinHistoryRepository coinHistoryRepository){
      CoinHistoryService.coinHistoryRepository = coinHistoryRepository;
  }

  //ユーザーの情報を新規作成
  @Async
  public CoinHistory addHistory(long userId, long coin, String remark) {
    CoinHistory history = new CoinHistory(userId, coin, remark, new Date());
    coinHistoryRepository.save(history);
    return history;
  }

  //ユーザーのコイン記録データを取得する
  @Async
  public List<CoinHistory> listByUserId(long userId) {
    return coinHistoryRepository.findByUserIdOrderByCreateTimeAsc(userId);
  }
}
