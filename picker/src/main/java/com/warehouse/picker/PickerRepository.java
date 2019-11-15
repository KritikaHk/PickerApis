package com.warehouse.picker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickerRepository extends CrudRepository<PickerDetailEntity, Integer> {

    PickerDetailEntity findByUserName(String userName);

}
