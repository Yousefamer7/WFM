package com.example.wfm.WFM.Services;

import com.example.wfm.WFM.Dtoes.CreateOrderDto;
import com.example.wfm.WFM.Dtoes.ResponseMessage;
import com.example.wfm.WFM.Models.OrderDetails;
import com.example.wfm.WFM.Models.Technicals;
import com.example.wfm.WFM.Repository.TechnicalRepo;
import com.example.wfm.WFM.Repository.WfmRepo;
import com.example.wfm.WFM.mapper.MapperDtos;
import oracle.net.aso.n;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
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


    public ResponseMessage assignTechnicals(int id, String techName){

        this.TechnicalRepo.findByName(techName).orElseThrow(() -> new RuntimeException("Worker with name " + techName + " does not exist"));

           if (wfmRepo.existsById(id)){
              int updatedRows = this.wfmRepo.updateTechName(techName, id);
              if(updatedRows == 0){
                  throw new RuntimeException("Not Assigned");
              }
              return new ResponseMessage("assigned");
           }
           return new ResponseMessage("this id is not exist");
    }

    public List<Technicals> getTechnicals(){
        List<Technicals> technicals =new ArrayList<>();
       return this.TechnicalRepo.findAll();

    }

}
