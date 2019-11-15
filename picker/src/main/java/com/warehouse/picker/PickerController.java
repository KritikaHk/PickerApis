package com.warehouse.picker;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/app")
public class PickerController {

    private static final Logger logger = LoggerFactory.getLogger(PickerController.class);

    @GetMapping(value = "/test")
    public String testController() {
        logger.info("Inside test controller");
        return "Working Fine !!!";
    }
}
