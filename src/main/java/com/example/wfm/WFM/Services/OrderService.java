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


       if ( !isvalidNumber(orderDto.getServiceNumber()))
           return new ResponseMessage(400,"number must be numeric and must be between 8 and 11");

        final OrderDetails save = this.wfmRepo.save(MapperDtos.convertCreateOrderDtoToOrderEntity(orderDto));
        return new ResponseMessage(200, "Order Created", save.getId());
    }


    public ResponseMessage assignTechnicals(int id, String techName){

        this.TechnicalRepo.findByName(techName).orElseThrow(() -> new RuntimeException("Worker with name " + techName + " does not exist"));

        Optional<OrderDetails> order = wfmRepo.findById(id);

        if(order.isEmpty()){
            throw new RuntimeException("No order with id " + id);
        }

        String assigned = order.get().getAssignedto();
        if (assigned != null && assigned.equals(techName)){
            return new ResponseMessage(400, "Order is assigned to this tech already");
        }

              int updatedRows = this.wfmRepo.updateTechName(techName, id);
              if(updatedRows == 0){
                  throw new RuntimeException("Not Assigned");
              }
              return new ResponseMessage(200, "Work order with id " +id+ " assigned to " + techName);
    }

    public List<Technicals> getTechnicals(){
        List<Technicals> technicals =new ArrayList<>();
       return this.TechnicalRepo.findAll();

    }

    public ResponseMessage getAllOrder(){
        List<OrderDetails> orders =new ArrayList<>();
        orders=this.wfmRepo.findAll();
        if (orders.isEmpty())
            return new ResponseMessage(400, "There is no Orders");

           return new ResponseMessage(200, "here are All Orders",orders);

    }


    public List<OrderDetails> GetAllOrderByDate(LocalDate dateTime ){
        return this.wfmRepo.findByVisitdate(dateTime);
    }

    public List<String> AvailableSlots(LocalDate dateTime,String name,Integer id){
        return   this.wfmRepo.findAvailableSlotsByWorkerAndDate(dateTime,name);
    }

    public ResponseMessage Schedule(LocalDate localDate,String name,String Slot,Integer id){

        List<String> availableSlots = this.wfmRepo.findAvailableSlotsByWorkerAndDate(localDate,name);
        final String s = this.wfmRepo.findslotById(id);
        if (availableSlots.isEmpty() || (s != null && s.equals(Slot))){
            return new ResponseMessage(400, "This worker is busy try another worker");
        }
        if (availableSlots.contains(Slot)){
            int updatedRows = this.wfmRepo.updateVisitDataAndSlots(id,localDate,Slot,name);
            if(updatedRows == 0){
                return new ResponseMessage(400, "Not Scedule");
            }
            return new ResponseMessage(200, "Work order with name " +name+ " is scheduled");

        }
        return  new ResponseMessage (200, "this slot is already exist try anothor slot");

    }

    public boolean isvalidNumber(String number){
        return  number != null && number.matches("\\d{8,11}");
    }

    public  ResponseMessage CloseOrder(Integer id){
        int updatedRows = this.wfmRepo.updateStatus(id);
        if(updatedRows == 0){
            throw new RuntimeException("Not close");
        }
        Optional<OrderDetails> order =this.wfmRepo.findById(id);

        if (order.get().getStatus().equals("closed")){
            return  new ResponseMessage(400,"this order is already close");
        }
        return new ResponseMessage(200,"order closed");
    }

}





