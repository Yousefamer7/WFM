package com.example.wfm.WFM.Models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "WFM")
@Builder
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ASSIGNEDTO")
    private String assignedto;
    @Column(name = "SLOT")
    private String slot;
    @Column(name = "VISITDATE")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime visitdate;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "SERVICENUMBER")
    private Integer servicenumber;



}
