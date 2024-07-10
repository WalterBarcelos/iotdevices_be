package com.walterbarcelos.iotdevices.controller;

import com.walterbarcelos.iotdevices.dto.DeviceDTO;
import com.walterbarcelos.iotdevices.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public List<DeviceDTO> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/{id}")
    public DeviceDTO getDeviceById(@PathVariable String id) {
        return deviceService.getDeviceById(id);
    }

    @PostMapping
    public DeviceDTO addDevice(@RequestBody DeviceDTO device) {
        return deviceService.createDevice(device);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}