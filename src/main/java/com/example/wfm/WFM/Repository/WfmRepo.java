package com.example.wfm.WFM.Repository;

import com.example.wfm.WFM.Models.OrderDetails;
import com.example.wfm.WFM.Models.Technicals;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WfmRepo extends JpaRepository<OrderDetails,Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE OrderDetails o SET o.assignedto  = :name, o.status='assigned' WHERE o.id= :id")
     int updateTechName(@Param("name") String name , @Param("id") Integer id);

    Optional<OrderDetails> findByAssignedto(String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE OrderDetails o SET o.visitdate  = :visitdate, o.slot= :slot ,o.status='schedule' WHERE o.id= :id")
    int updateVisitDataAndSlots(@Param("visitdate")LocalDateTime visitdate,@Param("slot") String slot,@Param("id")Integer id );


    List<OrderDetails> findByVisitdate(LocalDate visitdate);



    @Transactional
    @Query(value = "SELECT o.slot FROM OrderDetails o WHERE o.visitdate= :visitdate AND o.slot IS NOT NULL")
    List<String> findBussySlots(@Param("visitdate") LocalDate visitdate);


//    @Transactional
//    @Query(value = "select o from OrderDetails o  WHERE o.id= :id")
//    OrderDetails idIsExist(@Param("id") Integer id);
}
