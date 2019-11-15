package com.warehouse.picker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/inventory")
@CrossOrigin
public class InventoryForecastController {

  @Autowired
  private InventoryForecastCountRepository inventoryForecastCountRepository;

  @GetMapping("/forecast")
  private  Long getCountForZoneAndVariant(@RequestParam String zone,@RequestParam String variantId){

    return inventoryForecastCountRepository.count();
  }
}
