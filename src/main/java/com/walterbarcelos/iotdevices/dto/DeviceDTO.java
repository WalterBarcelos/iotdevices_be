package com.walterbarcelos.iotdevices.dto;

import lombok.Data;

@Data
public class DeviceDTO {
    private String id;
    private String name;
    private String mobileNumber;
    private String lastConnection;
    private double latitude;
    private double longitude;
}
