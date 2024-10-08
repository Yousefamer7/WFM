package com.example.wfm.WFM.Repository;

import com.example.wfm.WFM.Models.OrderDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WfmRepo extends JpaRepository<OrderDetails,Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE OrderDetails o SET o.assignedto  = :name, o.status='assigned' WHERE o.id= :id")
     int updateTechName(@Param("name") String name , @Param("id") Integer id);

}
