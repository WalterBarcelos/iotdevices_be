package com.walterbarcelos.iotdevices.service;

import com.walterbarcelos.iotdevices.dto.DeviceDTO;
import com.walterbarcelos.iotdevices.model.Device;
import com.walterbarcelos.iotdevices.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllDevices() {
        Device device = new Device();
        device.setId("1");
        device.setName("Device 1");
        device.setMobileNumber("1234567890");
        device.setLastConnection("2024-07-05T12:34:56Z");
        device.setLatitude(40.7128);
        device.setLongitude(-74.0060);

        when(deviceRepository.findAll()).thenReturn(Arrays.asList(device));

        List<DeviceDTO> devices = deviceService.getAllDevices();
        assertEquals(1, devices.size());
        verify(deviceRepository, times(1)).findAll();
    }

    @Test
    public void testGetDeviceById() {
        Device device = new Device();
        device.setId("1");
        device.setName("Device 1");
        device.setMobileNumber("1234567890");
        device.setLastConnection("2024-07-05T12:34:56Z");
        device.setLatitude(40.7128);
        device.setLongitude(-74.0060);

        when(deviceRepository.findById("1")).thenReturn(Optional.of(device));

        DeviceDTO deviceDTO = deviceService.getDeviceById("1");
        assertEquals(device.getId(), deviceDTO.getId());
        verify(deviceRepository, times(1)).findById("1");
    }

    @Test
    public void testCreateDevice() {
        Device device = new Device();
        device.setName("Device 1");
        device.setMobileNumber("1234567890");
        device.setLastConnection("2024-07-05T12:34:56Z");
        device.setLatitude(40.7128);
        device.setLongitude(-74.0060);

        Device savedDevice = new Device();
        savedDevice.setId("1");
        savedDevice.setName("Device 1");
        savedDevice.setMobileNumber("1234567890");
        savedDevice.setLastConnection("2024-07-05T12:34:56Z");
        savedDevice.setLatitude(40.7128);
        savedDevice.setLongitude(-74.0060);

        when(deviceRepository.save(any(Device.class))).thenReturn(savedDevice);

        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setName("Device 1");
        deviceDTO.setMobileNumber("1234567890");
        deviceDTO.setLastConnection("2024-07-05T12:34:56Z");
        deviceDTO.setLatitude(40.7128);
        deviceDTO.setLongitude(-74.0060);

        DeviceDTO createdDevice = deviceService.createDevice(deviceDTO);
        assertEquals(savedDevice.getId(), createdDevice.getId());
        verify(deviceRepository, times(1)).save(any(Device.class));
    }

    @Test
    public void testDeleteDevice() {
        doNothing().when(deviceRepository).deleteById("1");
        deviceService.deleteDevice("1");
        verify(deviceRepository, times(1)).deleteById("1");
    }
}
