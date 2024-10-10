package com.example.wfm.WFM.Models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "TECHNICAL_SLOT")
@Data
@Builder
public class Technicals_Slots {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    @SequenceGenerator(name = "mySeqGen", sequenceName = "WFM_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private  Integer id;

    @Column(name = "TIME_SLOT")
    private  String slot;

}
