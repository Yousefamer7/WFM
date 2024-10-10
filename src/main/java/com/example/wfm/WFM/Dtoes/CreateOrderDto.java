package com.example.wfm.WFM.Dtoes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.processing.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderDto {


    private String address;
    private String serviceNumber;

}
