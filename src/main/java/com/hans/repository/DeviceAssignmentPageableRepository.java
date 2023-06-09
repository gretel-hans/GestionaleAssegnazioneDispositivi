package com.hans.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hans.model.DeviceAssignment;

@Repository
public interface DeviceAssignmentPageableRepository extends PagingAndSortingRepository<DeviceAssignment, Long>{

}
