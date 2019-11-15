package com.warehouse.picker;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/app")
public class PickerController {

    private static final Logger logger = LoggerFactory.getLogger(PickerController.class);


    @Autowired
    PickerRepository pickerRepository;

    @Autowired
    PickListRepository pickListRepository;

    @GetMapping(value = "/test")
    public String testController() {
        logger.info("Inside test controller");
        return "Working Fine !!!";
    }


    @GetMapping("/orders")
    public List<Integer> fetchOrderForUsers(@RequestParam("username")  String userName){
        List<PickerDetailEntity> pickerDetailEntityList = pickerRepository.findByUserName(userName);
        List<Integer> orderIds = new ArrayList<>();
        if(!pickerDetailEntityList.isEmpty()){
            for(PickerDetailEntity pickerDetailEntity : pickerDetailEntityList){
                orderIds.add(pickerDetailEntity.getOrderId());
            }
        }
        return orderIds;
    }
    
    @GetMapping("/authLoin")
    public boolean authenticateUsers(@RequestParam("username")  String userName){
        List<PickerDetailEntity> pickerDetailEntityList = pickerRepository.findByUserName(userName);
        return (!pickerDetailEntityList.isEmpty());
    }
    

    @GetMapping("/checkout")
    public String checkoutBarcode(@RequestParam("barcode")  String barcode,
    @RequestParam("userName")  String userName, @RequestParam("userName")  Long orderId){

     return null;
    }


    @GetMapping("/picklist")
    public List<PickList> fetchPickList(@RequestParam("orderid") Long orderId){
         return pickListRepository.findByOrderId(orderId);
    }

}
