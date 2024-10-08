package com.example.wfm.WFM.Repository;

import com.example.wfm.WFM.Models.Technicals_Slots;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlotRepo extends JpaRepository<Technicals_Slots,Integer> {

//    List<Technicals_Slots> getSlotsById();
}
