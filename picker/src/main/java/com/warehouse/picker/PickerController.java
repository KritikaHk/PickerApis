package com.warehouse.picker;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/app")
public class PickerController {

    private static final Logger logger = LoggerFactory.getLogger(PickerController.class);

    private static final String successMessage = "Success";
    private static final String failMessage = "Fail";

    @Autowired
    PickerRepository pickerRepository;

    @Autowired
    PickListRepository pickListRepository;
    @Autowired
    NutDetailRepository nutDetailRepository;

    @GetMapping(value = "/test")
    public String testController() {
        logger.info("Inside test controller");
        return "Working Fine !!!";
    }


    @GetMapping("/orders")
    public ApiResponse fetchOrderForUsers(@RequestParam("username") String userName) {
        List<PickerDetailEntity> pickerDetailEntityList = pickerRepository.findByUserName(userName);
        List<Long> orderIds = new ArrayList<>();
        pickerDetailEntityList.forEach(pickerDetailEntity ->
                orderIds.add(Long.valueOf(pickerDetailEntity.getOrderId())));

        List<PickList> pickLists = pickListRepository.findByOrderIdIn(orderIds);

        List<VariantQtyDto> variantQtyDtoList;
        Map<String, List<VariantQtyDto>> map = new HashMap<>();
        for (PickList pickList : pickLists) {
            VariantQtyDto variantQtyDto = new VariantQtyDto();
            variantQtyDto.setQty(pickList.getTotalQuantity());
            variantQtyDto.setVariantId(pickList.getVariantId());
            if (map.get(pickList.getBin()) == null) {
                List<VariantQtyDto> variantQtyDtos = new ArrayList<>();
                variantQtyDtos.add(variantQtyDto);
                map.put(pickList.getBin(), variantQtyDtos);
            } else {
                List<VariantQtyDto> variantQtyDtos1 = map.get(pickList.getBin());
                variantQtyDtos1.add(variantQtyDto);
                map.put(pickList.getBin(), variantQtyDtos1);
            }
        }

//        DtoWrapper dtoWrapper = new DtoWrapper();
        List<PicklistDto> picklistDtos = new ArrayList<>();
        for (Map.Entry<String, List<VariantQtyDto>> entry : map.entrySet()) {
            PicklistDto picklistDto = new PicklistDto();
            picklistDto.setBin(entry.getKey());
            picklistDto.setVariantQtyDto(entry.getValue());
            picklistDtos.add(picklistDto);
//            dtoWrapper.setData(picklistDtos);
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage(successMessage);
        apiResponse.setData(picklistDtos);
        return apiResponse;
//        return fetchOrderDetails(userName);
    }


    @GetMapping("/orders/bin")
    public ApiResponse fetchNutForBin(@RequestParam("username") String userName , @ RequestParam("bin_id") String binbarcode) {
        List<PickerDetailEntity> pickerDetailEntityList = pickerRepository.findByUserName(userName);
        List<Long> orderIds = new ArrayList<>();
        pickerDetailEntityList.forEach(pickerDetailEntity ->
                orderIds.add(Long.valueOf(pickerDetailEntity.getOrderId())));

        List<PickList> pickLists = pickListRepository.findByBinAndOrderIdIn(binbarcode,orderIds);

        List<VariantQtyDto> variantQtyDtoList = new ArrayList<>();
        for (PickList pickList : pickLists) {
            VariantQtyDto variantQtyDto = new VariantQtyDto();
            variantQtyDto.setQty(pickList.getTotalQuantity());
            variantQtyDto.setVariantId(pickList.getVariantId());
            variantQtyDtoList.add(variantQtyDto);
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMessage(successMessage);
        apiResponse.setData(variantQtyDtoList);
        return apiResponse;
    }



    @GetMapping("/orders/bin/checkout")
    public CheckedOutResponseWrapper checkoutBarcode(@RequestParam("barcode") String barcode, @RequestParam("username") String userName, @RequestParam("variantid") String variantId ) {
        NutDetailEntity nutDetailEntity = nutDetailRepository.findByBarcode(barcode);
        if (nutDetailEntity != null) {
            List<PickerDetailEntity> pickerDetailEntityList = pickerRepository.findByUserName(userName);
            for (PickerDetailEntity pickerDetailEntity : pickerDetailEntityList) {
                List<PickList> pickLists = pickListRepository.findByOrderId(Long.valueOf(pickerDetailEntity.getOrderId()));
                for (PickList pickList : pickLists) {
                    pickList.setTotalQuantity(pickList.getTotalQuantity() - 1);
                    pickListRepository.save(pickList);
                    CheckedOutResponse checkedOutResponse = new CheckedOutResponse();
                    checkedOutResponse.setOrderId(pickList.getOrderId());
                    checkedOutResponse.setQty(pickList.getTotalQuantity());
                    checkedOutResponse.setVariantId(variantId);
                    checkedOutResponse.setCheckedOutSuccess(true);
                    CheckedOutResponseWrapper checkedOutResponseWrapper = new CheckedOutResponseWrapper();
                    checkedOutResponseWrapper.setData(Collections.singletonList(checkedOutResponse));
                    return  checkedOutResponseWrapper;
                }
            }
        }
        return new CheckedOutResponseWrapper();
    }





    @GetMapping("/login")
    public LoginApiResponse loginUser(@RequestParam("username") String userName, @RequestParam("password") String password) {
        LoginApiResponse apiResponse = new LoginApiResponse();
        if (userName.equals("picker1") && password.equals("picker1")) {
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setMessage(successMessage);
            apiResponse.setSuccess(true);
            apiResponse.setData(null);
            return apiResponse;
        } else if (userName.equals("picker2") && password.equals("picker2")) {
            apiResponse.setCode(HttpStatus.OK.value());
            apiResponse.setMessage(successMessage);
            apiResponse.setSuccess(true);
            apiResponse.setData(null);
            return apiResponse;
        } else {
            apiResponse.setCode(HttpStatus.UNAUTHORIZED.value());
            apiResponse.setMessage(failMessage);
            apiResponse.setSuccess(false);
            apiResponse.setData(null);
            return apiResponse;
        }
    }

    @GetMapping("/picklist")
    public List<PickList> fetchPickList(@RequestParam("orderid") Long orderId) {
        return pickListRepository.findByOrderId(orderId);
    }



    private DtoWrapper fetchOrderDetails(String userName) {
        List<PickerDetailEntity> pickerDetailEntityList = pickerRepository.findByUserName(userName);
        List<Long> orderIds = new ArrayList<>();
        if (!pickerDetailEntityList.isEmpty()) {
            for (PickerDetailEntity pickerDetailEntity : pickerDetailEntityList) {
                orderIds.add(Long.valueOf(pickerDetailEntity.getOrderId()));
            }
        }


        List<PicklistDto> picklistDtos = new ArrayList<>();
        PicklistDto picklistDto = new PicklistDto();

        List<VariantQtyDto> variantQtyDtos = new ArrayList<>();

        // all product data for first bin

        VariantQtyDto variantQtyDto = new VariantQtyDto();
        variantQtyDto.setVariantId("NUT5282-01");
        variantQtyDto.setQty(2L);
        VariantQtyDto variantQtyDto1 = new VariantQtyDto();
        variantQtyDto1.setVariantId("NUT1367-02");
        variantQtyDto1.setQty(3L);
        variantQtyDtos.add(variantQtyDto);
        variantQtyDtos.add(variantQtyDto1);
        picklistDto.setBin("HKR1-01-02");
        picklistDto.setVariantQtyDto(variantQtyDtos);
        picklistDtos.add(picklistDto);

        // all product data for bin


        PicklistDto picklistDto1 = new PicklistDto();
        List<VariantQtyDto> variantQtyDtos1 = new ArrayList<>();
        VariantQtyDto variantQtyDto2 = new VariantQtyDto();
        variantQtyDto2.setVariantId("NUT5123-12");
        variantQtyDto2.setQty(3L);
        VariantQtyDto variantQtyDto3 = new VariantQtyDto();
        variantQtyDto3.setVariantId("NUT1334-03");
        variantQtyDto3.setQty(5L);
        variantQtyDtos1.add(variantQtyDto2);
        variantQtyDtos1.add(variantQtyDto3);
        picklistDto1.setBin("HKR1-01-06");
        picklistDto1.setVariantQtyDto(variantQtyDtos1);
        picklistDtos.add(picklistDto1);


        DtoWrapper dtoWrapper = new DtoWrapper();
        dtoWrapper.setData(picklistDtos);

        return dtoWrapper;

    }

    @Data
    class DtoWrapper {
        private List<PicklistDto> data;
    }

    @Data
    class LoginApiResponse extends ApiResponse{
        private boolean success;
    }

    @Data
    class  CheckedOutResponse{
        private String variantId;
        private Long qty;
        private boolean checkedOutSuccess;
        private Long orderId;
    }

    @Data
    class CheckedOutResponseWrapper {
        private List<CheckedOutResponse> data;
    }
}
