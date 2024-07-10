package com.walterbarcelos.iotdevices.repository;

import com.walterbarcelos.iotdevices.model.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {
}