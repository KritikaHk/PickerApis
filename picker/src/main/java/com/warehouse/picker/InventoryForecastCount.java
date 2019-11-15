package com.warehouse.picker;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "inv_forecast")
@Data
public class InventoryForecastCount {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "variant_id")
  private String variantId;

  @Column(name = "zone")
  private String zone;

  @Column(name = "inv_count")
  private Long invCount;


}
