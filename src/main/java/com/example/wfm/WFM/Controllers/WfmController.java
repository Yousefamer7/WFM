package com.example.wfm.WFM.Controllers;


import com.example.wfm.WFM.Dtoes.CreateOrderDto;
import com.example.wfm.WFM.Dtoes.ResponseMessage;
import com.example.wfm.WFM.Models.OrderDetails;
import com.example.wfm.WFM.Services.OrderService;
import oracle.net.aso.r;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wfm")
public class WfmController {

        @Autowired
        private OrderService orderService;


        @PostMapping("/createOrder")
        public ResponseMessage addOrder(@RequestBody CreateOrderDto orderDto){
               this.orderService.CreateOrder(orderDto);
                return new ResponseMessage("Order Created");
        }

}
