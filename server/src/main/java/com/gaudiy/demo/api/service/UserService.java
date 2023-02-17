package com.gaudiy.demo.api.service;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CompletableFuture;
import com.gaudiy.demo.api.repo.*;
import com.gaudiy.demo.api.model.db.*;

@Service
public class UserService {

  private static UserRepository userRepository;

  @Autowired
  public void setUserRepository(UserRepository userRepository){
      UserService.userRepository = userRepository;
  }

  @Async
  public User register(String email) {
    User user = new User(email, new Date());
    userRepository.save(user);
    return user;
  }

}
