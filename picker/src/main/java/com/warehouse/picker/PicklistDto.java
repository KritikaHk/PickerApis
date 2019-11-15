package com.warehouse.picker;

import lombok.Data;

import java.util.List;

@Data
public class PicklistDto {

    private String bin;

    private List<VariantQtyDto> variantQtyDto;
}
