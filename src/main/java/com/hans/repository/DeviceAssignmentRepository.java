package com.hans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hans.model.DeviceAssignment;

@Repository
public interface DeviceAssignmentRepository extends JpaRepository<DeviceAssignment, Long>{

}
