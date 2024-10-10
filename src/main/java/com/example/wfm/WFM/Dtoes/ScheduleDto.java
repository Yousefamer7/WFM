package com.example.wfm.WFM.Dtoes;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.net.aso.p;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleDto {

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate visitdate;
    private String assignedto;
    private  String slot;


}
