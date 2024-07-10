package com.walterbarcelos.iotdevices.service;

import com.walterbarcelos.iotdevices.dto.DeviceDTO;
import com.walterbarcelos.iotdevices.model.Device;
import com.walterbarcelos.iotdevices.repository.DeviceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    
    public List<DeviceDTO> getAllDevices() {
        return deviceRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public DeviceDTO getDeviceById(String id) {
        return deviceRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public DeviceDTO createDevice(DeviceDTO deviceDTO) {
        Device device = convertToEntity(deviceDTO);
        device.setId(null);  // Ensure ID is null so MongoDB generates it
        Device savedDevice = deviceRepository.save(device);
        return convertToDTO(savedDevice);
    }

    public void deleteDevice(String id) {
        deviceRepository.deleteById(id);
    }

    private DeviceDTO convertToDTO(Device device) {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(device.getId());
        deviceDTO.setName(device.getName());
        deviceDTO.setMobileNumber(device.getMobileNumber());
        deviceDTO.setLastConnection(device.getLastConnection());
        deviceDTO.setLatitude(device.getLatitude());
        deviceDTO.setLongitude(device.getLongitude());
        return deviceDTO;
    }

    private Device convertToEntity(DeviceDTO deviceDTO) {
        Device device = new Device();
        device.setId(deviceDTO.getId());
        device.setName(deviceDTO.getName());
        device.setMobileNumber(deviceDTO.getMobileNumber());
        device.setLastConnection(deviceDTO.getLastConnection());
        device.setLatitude(deviceDTO.getLatitude());
        device.setLongitude(deviceDTO.getLongitude());
        return device;
    }
}
