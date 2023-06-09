package com.hans.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hans.model.Device;

@Repository
public interface DevicePageableRepository extends PagingAndSortingRepository<Device, Long>{

}
