package com.warehouse.picker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InventoryForecastCountRepository extends CrudRepository<InventoryForecastCount, Integer> {

  InventoryForecastCount findByVariantIdZone(String variantId,String zone);

}
