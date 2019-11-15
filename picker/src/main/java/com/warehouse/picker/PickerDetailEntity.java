package com.warehouse.picker;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="picker_detail")
public class PickerDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "order_id")
    private Integer orderId;
}
