package com.example.wfm.WFM.Repository;

import com.example.wfm.WFM.Models.OrderDetails;
import com.example.wfm.WFM.Models.Technicals;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WfmRepo extends JpaRepository<OrderDetails,Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE OrderDetails o SET o.assignedto  = :name, o.status='assigned' WHERE o.id= :id")
     int updateTechName(@Param("name") String name , @Param("id") Integer id);

    Optional<OrderDetails> findByAssignedto(String name);

//    @Transactional
//    @Query(value = "select o from OrderDetails o  WHERE o.id= :id")
//    OrderDetails idIsExist(@Param("id") Integer id);
}
