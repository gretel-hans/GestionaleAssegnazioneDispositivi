package com.hans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hans.model.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long>{

}
