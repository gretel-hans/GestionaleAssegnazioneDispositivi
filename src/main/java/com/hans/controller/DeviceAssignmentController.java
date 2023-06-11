package com.hans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hans.model.Device;
import com.hans.model.DeviceAssignment;
import com.hans.service.DeviceAssignmentService;

@RestController
@RequestMapping("/deviceManagement/deviceAssignment")
public class DeviceAssignmentController {

	@Autowired DeviceAssignmentService dvaService;
	
	@GetMapping()
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<DeviceAssignment>> getAllDevicesAssignment() {
		return new ResponseEntity<>(dvaService.findAllAssignments(), HttpStatus.OK);
	}
	
	@GetMapping("/page")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<DeviceAssignment>> getAllDevicesAssignmentPage(Pageable p) {
		return new ResponseEntity<>(dvaService.findAllAssignmentsPage(p), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getDeviceAssignment(@PathVariable Long id) {
			return new ResponseEntity<>(dvaService.findAssignment(id), HttpStatus.OK);			
	}
	
	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createDeviceAssignment(@RequestBody DeviceAssignment d) {
				if(dvaService.createOrUpdateAssignment(d)) {
					return new ResponseEntity<>(dvaService.findAssignment(d.getId()), HttpStatus.CREATED);
				}else 
					return new ResponseEntity<>("ERROR!!", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateDeviceAssignment(@RequestBody DeviceAssignment da, @PathVariable Long id) {
		if(id==da.getId()&&dvaService.createOrUpdateAssignment(da)) {
			
			return new ResponseEntity<>(da, HttpStatus.OK);			
		}else
			return new ResponseEntity<>("ERROR!!", HttpStatus.BAD_REQUEST);			
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteDeviceAssignment(@PathVariable Long id) {
	return new ResponseEntity<>(dvaService.deleteAssignment(id), HttpStatus.OK);
	}
}