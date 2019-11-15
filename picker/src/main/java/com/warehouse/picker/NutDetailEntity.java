package com.warehouse.picker;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "nut_detail")
@Entity
public class NutDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "nut_id")
    private Integer nutId;
}
