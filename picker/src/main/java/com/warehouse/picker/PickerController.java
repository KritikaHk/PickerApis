package com.warehouse.picker;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/test")
    public String testController() {
        logger.info("Inside test controller");
        return "Working Fine !!!";
    }


    @GetMapping("/orders")
    public String[] fetchOrderForUsers(@RequestParam("username")  String userName){
        PickerDetailEntity pickerDetailEntity = pickerRepository.findByUserName(userName);
        if(pickerDetailEntity != null){
            String [] order = (pickerDetailEntity.getOrderId().toString().split(","));
            return  order;
        }
        return null;
    }




}
