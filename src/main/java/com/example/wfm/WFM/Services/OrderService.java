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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        final OrderDetails save = this.wfmRepo.save(MapperDtos.convertCreateOrderDtoToOrderEntity(orderDto));


        return new ResponseMessage("Order Created", save.getId());

    }


    public ResponseMessage assignTechnicals(int id, String techName){

        this.TechnicalRepo.findByName(techName).orElseThrow(() -> new RuntimeException("Worker with name " + techName + " does not exist"));

        Optional<OrderDetails> order = wfmRepo.findById(id);

        if(order.isEmpty()){
            throw new RuntimeException("No order with id " + id);
        }

        String assigned = order.get().getAssignedto();
        if (assigned != null && assigned.equals(techName)){
            return new ResponseMessage("Order is assigned to this tech already");
        }

              int updatedRows = this.wfmRepo.updateTechName(techName, id);
              if(updatedRows == 0){
                  throw new RuntimeException("Not Assigned");
              }
              return new ResponseMessage("Work order with id " +id+ " assigned to " + techName);
    }

    public List<Technicals> getTechnicals(){
        List<Technicals> technicals =new ArrayList<>();
       return this.TechnicalRepo.findAll();

    }


    public ResponseMessage ScheduleTechnicals(LocalDate dateTime){
        return new ResponseMessage("get", this.wfmRepo.findByVisitdate(dateTime));
    }
}





