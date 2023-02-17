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

//ユーザーDBを操作するclass
@Service
public class UserService {

  //使われるものをinitialする
  private static UserRepository userRepository;

  //使われるものを注入する
  @Autowired
  public void setUserRepository(UserRepository userRepository){
      UserService.userRepository = userRepository;
  }

  //ユーザーの情報を新規作成
  @Async
  public User register(String email) {
    User user = new User(email, 0L, new Date());
    userRepository.save(user);
    return user;
  }

  //ユーザーのデータを取得する、エラーの場合はException
  @Async
  public User dataById(long userId) throws Exception {
    return userRepository.findById(userId).orElseThrow(() -> new Exception("User Not Found"));
  }

  //ユーザーのデータをアップデートする
  @Async
  public User update(User user) {
    userRepository.save(user);
    return user;
  }
}
