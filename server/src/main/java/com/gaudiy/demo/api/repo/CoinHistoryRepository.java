package com.gaudiy.demo.api.repo;

import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.gaudiy.demo.api.model.db.*;

//コイン記録をDBに接続のadapter
@Repository("CoinHistoryRepository")
public interface CoinHistoryRepository extends JpaRepository<CoinHistory, Long> {
    //記録を取得するためにdefinedした
    List<CoinHistory> findByUserIdOrderByCreateTimeAsc(long userId);
}
