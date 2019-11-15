package com.warehouse.picker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickListRepository extends CrudRepository<PickList,Integer> {

    List<PickList> findByOrderId(Long orderId);

    List<PickList> findByOrderIdIn(List<Long> orderId);

}
