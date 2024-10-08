package com.example.wfm.WFM.Services;

import com.example.wfm.WFM.Dtoes.CreateOrderDto;
import com.example.wfm.WFM.Models.OrderDetails;
import com.example.wfm.WFM.Repository.WfmRepo;
import com.example.wfm.WFM.mapper.MapperDtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private WfmRepo wfmRepo;



    public OrderDetails CreateOrder(CreateOrderDto orderDto){

       return this.wfmRepo.save(MapperDtos.convertCreateOrderDtoToOrderEntity(orderDto));

    }

}
