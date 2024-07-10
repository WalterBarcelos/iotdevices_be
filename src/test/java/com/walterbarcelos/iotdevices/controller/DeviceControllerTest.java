package com.walterbarcelos.iotdevices.controller;

import com.walterbarcelos.iotdevices.dto.DeviceDTO;
import com.walterbarcelos.iotdevices.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DeviceControllerTest {

    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private DeviceController deviceController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
    }

    @Test
    public void testGetAllDevices() throws Exception {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId("1");
        deviceDTO.setName("Device 1");
        deviceDTO.setMobileNumber("1234567890");
        deviceDTO.setLastConnection("2024-07-05T12:34:56Z");
        deviceDTO.setLatitude(40.7128);
        deviceDTO.setLongitude(-74.0060);

        when(deviceService.getAllDevices()).thenReturn(Arrays.asList(deviceDTO));

        mockMvc.perform(get("/api/devices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(deviceDTO.getId()));

        verify(deviceService, times(1)).getAllDevices();
    }

    @Test
    public void testGetDeviceById() throws Exception {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId("1");
        deviceDTO.setName("Device 1");
        deviceDTO.setMobileNumber("1234567890");
        deviceDTO.setLastConnection("2024-07-05T12:34:56Z");
        deviceDTO.setLatitude(40.7128);
        deviceDTO.setLongitude(-74.0060);

        when(deviceService.getDeviceById("1")).thenReturn(deviceDTO);

        mockMvc.perform(get("/api/devices/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(deviceDTO.getId()));

        verify(deviceService, times(1)).getDeviceById("1");
    }

    @Test
    public void testCreateDevice() throws Exception {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId("1");
        deviceDTO.setName("Device 1");
        deviceDTO.setMobileNumber("1234567890");
        deviceDTO.setLastConnection("2024-07-05T12:34:56Z");
        deviceDTO.setLatitude(40.7128);
        deviceDTO.setLongitude(-74.0060);

        when(deviceService.createDevice(any(DeviceDTO.class))).thenReturn(deviceDTO);

        mockMvc.perform(post("/api/devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Device 1\", \"mobileNumber\": \"1234567890\", \"lastConnection\": \"2024-07-05T12:34:56Z\", \"latitude\": 40.7128, \"longitude\": -74.0060}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(deviceDTO.getId()));

        verify(deviceService, times(1)).createDevice(any(DeviceDTO.class));
    }

    @Test
    public void testDeleteDevice() throws Exception {
        doNothing().when(deviceService).deleteDevice("1");

        mockMvc.perform(delete("/api/devices/1"))
                .andExpect(status().isNoContent());

        verify(deviceService, times(1)).deleteDevice("1");
    }
}
