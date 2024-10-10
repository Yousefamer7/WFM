package com.example.wfm.WFM.Repository;

import com.example.wfm.WFM.Models.Technicals_Slots;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SlotRepo extends JpaRepository<Technicals_Slots,Integer> {

//    List<Technicals_Slots> getSlotsById();




}
