package com.walterbarcelos.iotdevices.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Document(collection = "devices")
public class Device {
    @Id
    private String id;
    private String name;
    private String mobileNumber;
    private String lastConnection;
    private double latitude;
    private double longitude;
}
