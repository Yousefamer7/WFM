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
    int updateTechName(@Param("name") String name, @Param("id") Integer id);

    Optional<OrderDetails> findByAssignedto(String name);


    List<OrderDetails> findByVisitdate(LocalDate visitdate);


    @Transactional
    @Query(value = "select o.slot from OrderDetails o where o.id = :id ")
    String findslotById(@Param("id") Integer id);

    //    this to find bussy slots to specific worker
//    @Query(value = "SELECT o.slot FROM OrderDetails o WHERE o.visitdate= :visitdate AND o.slot IS NOT NULL")
    @Transactional
    @Query(value = "SELECT T.slot FROM Technicals_Slots T WHERE T.slot NOT IN (" + "SELECT o.slot FROM OrderDetails o WHERE o.assignedto = :assignedto AND o.visitdate = :visitdate AND o.slot is not null)")
    List<String> findAvailableSlotsByWorkerAndDate(@Param("visitdate") LocalDate visitdate, @Param("assignedto") String assignedto);

    @Modifying
    @Transactional
    @Query(value = "UPDATE OrderDetails o SET o.visitdate  = :visitdate, o.slot= :slot ,o.status='schedule' WHERE o.assignedto= :assignedto and o.id= :id")
    int updateVisitDataAndSlots(@Param("id") Integer id,@Param("visitdate") LocalDate visitdate, @Param("slot") String slot, @Param("assignedto") String assignedto);
}