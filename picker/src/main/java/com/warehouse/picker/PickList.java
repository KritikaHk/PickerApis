package com.warehouse.picker;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "picklist")
@Data
public class PickList {

    private static final long serialVersionUID = 8604958178118725125L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "variant_id")
    private String variantId;

    @Column(name = "variant_name")
    private String variantName;

    private String batch;

    @Column(name = "box_barcode")
    private String boxBarcode;


    @Column(name = "total_quantity")
    private Long totalQuantity;

    private String bin;

    private Integer mfg;

    private Double mrp;

    @Column(name = "box_size")
    private Integer boxSize;
}
