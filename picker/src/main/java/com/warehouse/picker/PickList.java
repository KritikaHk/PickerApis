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

    /**
     * Total quantity of items for a boxSize requested for this variant against an order
     */
    @Column(name = "total_quantity")
    private Long totalQuantity;

    private String bin;

    private Integer mfg;

    transient private boolean picked;

    private Boolean partial;

    private Double mrp;

    /**
     * total quantity of items of a given boxSize and a batch booked against an order
     */
    @Column(name = "quantity_from_batch")
    private Long quantityFromBatch;

    @Column(name = "box_size")
    private Integer boxSize;

    @Column(name = "bin_type")
    private String binType;

    @Column(name = "box_type")
    private String boxType;

    private String name;

    @Column(name = "bin_internal_id")
    private Long binInternalId;

    @Column(name = "img_src")
    private String imgSrc;

    private boolean freebie;

}
