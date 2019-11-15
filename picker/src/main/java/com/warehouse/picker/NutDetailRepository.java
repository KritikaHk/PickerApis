package com.warehouse.picker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutDetailRepository extends CrudRepository<NutDetailEntity, Long> {

    NutDetailEntity findByBarcode(String barcode);
}
