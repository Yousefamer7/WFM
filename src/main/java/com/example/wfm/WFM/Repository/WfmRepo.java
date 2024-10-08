package com.example.wfm.WFM.Repository;

import com.example.wfm.WFM.Models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WfmRepo extends JpaRepository<OrderDetails,Integer> {


//    @Query(value = "", nativeQuery = true)
//    boolean isOrderExist(int id);
}
