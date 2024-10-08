package com.example.wfm.WFM.mapper;

import com.example.wfm.WFM.Dtoes.CreateOrderDto;
import com.example.wfm.WFM.Models.OrderDetails;

public class MapperDtos {


    public static OrderDetails convertCreateOrderDtoToOrderEntity(CreateOrderDto orderDto){

        return OrderDetails.builder().
                address(orderDto.getAddress()).
                servicenumber(orderDto.getServiceNumber())
                .build();
    }

    public static CreateOrderDto Order_To_Dto(OrderDetails orderDetails){
        return  CreateOrderDto.builder().
                address(orderDetails.getAddress())
                .serviceNumber(orderDetails.getServicenumber())
                .build();
    }
}
