package com.example.wfm.WFM.Controllers;


import com.example.wfm.WFM.Dtoes.CreateOrderDto;
import com.example.wfm.WFM.Dtoes.DateDto;
import com.example.wfm.WFM.Dtoes.ResponseMessage;
import com.example.wfm.WFM.Dtoes.ScheduleDto;
import com.example.wfm.WFM.Models.OrderDetails;
import com.example.wfm.WFM.Models.Technicals;
import com.example.wfm.WFM.Repository.TechnicalRepo;
import com.example.wfm.WFM.Services.OrderService;
import oracle.net.aso.r;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wfm")
public class WfmController {

        @Autowired
        private OrderService orderService;


        @PostMapping("/createOrder")
        public ResponseMessage addOrder(@RequestBody CreateOrderDto orderDto){
            return  this.orderService.CreateOrder(orderDto);
        }

        @GetMapping("/assignOrder")
        public ResponseMessage assignTechnical(@RequestParam int id, @RequestParam String name){
               return  this.orderService.assignTechnicals(id, name);
        }

        @GetMapping("/getAllTechnicals")
        public List<Technicals> getAllTechnicals(){
            return  this.orderService.getTechnicals();
        }

        @GetMapping("/getAllOrder")
        public  ResponseMessage getAllOrder(){
            return this.orderService.getAllOrder();
        }

        @GetMapping("/getAllOrderByDate")
        public  List<OrderDetails> GetAllOrderByDateFromDb(@RequestBody DateDto dateDto){
            return this.orderService.GetAllOrderByDate(dateDto.getVisitdate());
        }


        @GetMapping("/GetAllAvailableSlots")
        public List<String> GetAllAvailableSlots(@RequestBody ScheduleDto scheduleDto){
            return this.orderService.AvailableSlots(scheduleDto.getVisitdate(),scheduleDto.getAssignedto(),scheduleDto.getId());
        }

        @GetMapping("/schedule")
        public ResponseMessage ScheduleOrdersInDb(@RequestBody ScheduleDto scheduleDto){
            return this.orderService.Schedule(scheduleDto.getVisitdate(),scheduleDto.getAssignedto(),scheduleDto.getSlot(),scheduleDto.getId());
        }


        @GetMapping("/CloseOrder")
        public ResponseMessage CloseOrder(@RequestParam Integer id ){

            return this.orderService.CloseOrder(id);

        }






}
