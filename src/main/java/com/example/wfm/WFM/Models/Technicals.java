package com.example.wfm.WFM.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TECHNICAL")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Technicals {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGen")
    @SequenceGenerator(name = "mySeqGen", sequenceName = "WFM_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;

}
