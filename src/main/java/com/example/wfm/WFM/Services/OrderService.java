package com.example.wfm.WFM.Services;

import com.example.wfm.WFM.Dtoes.CreateOrderDto;
import com.example.wfm.WFM.Dtoes.ResponseMessage;
import com.example.wfm.WFM.Models.OrderDetails;
import com.example.wfm.WFM.Repository.TechnicalRepo;
import com.example.wfm.WFM.Repository.WfmRepo;
import com.example.wfm.WFM.mapper.MapperDtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private WfmRepo wfmRepo;

    @Autowired
    private TechnicalRepo TechnicalRepo;


    public ResponseMessage CreateOrder(CreateOrderDto orderDto){

      this.wfmRepo.save(MapperDtos.convertCreateOrderDtoToOrderEntity(orderDto));

      return new ResponseMessage("Order Created");

    }


    public Optional<OrderDetails> assignTechnicals(int id, String techName){
        int updatedRows = this.wfmRepo.updateTechName(techName, id);
        if(updatedRows == 0){
            throw new RuntimeException("Not Assigned");
        }
        return  wfmRepo.findById(id);
    }

}
