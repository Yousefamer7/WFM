package com.example.wfm.WFM.Repository;

import com.example.wfm.WFM.Models.Technicals;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TechnicalRepo extends JpaRepository<Technicals,Integer> {



    Optional<Technicals> findByName(String name);
}
