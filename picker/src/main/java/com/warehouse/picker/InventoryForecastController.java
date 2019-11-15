package com.warehouse.picker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/inventory")
public class InventoryForecastController {

  @Autowired
  private InventoryForecastCountRepository inventoryForecastCountRepository;

  @GetMapping("/forecast")
  private  Long getCountForZoneAndVariant(@RequestParam String zone,@RequestParam String variantId){

    return inventoryForecastCountRepository.findByVariantIdZone(zone,variantId).getInvCount();
  }
}
