package com.gaudiy.demo.api.repo;

import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.gaudiy.demo.api.model.db.*;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

}
