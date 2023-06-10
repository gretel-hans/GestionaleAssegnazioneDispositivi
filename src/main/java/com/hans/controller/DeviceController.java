package com.hans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hans.model.Device;
import com.hans.service.DeviceService;

@RestController
@RequestMapping("/deviceManagement/devices")
public class DeviceController {

	@Autowired DeviceService deviceService;
	
	@GetMapping()
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Device>> getAllDevices() {
		return new ResponseEntity<>(deviceService.findAllDevices(), HttpStatus.OK);
	}
	
	@GetMapping("/page")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Page<Device>> getAllDevicesPage(Pageable p) {
		return new ResponseEntity<>(deviceService.findAllDevicesPageable(p), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getDevice(@PathVariable Long id) {
			return new ResponseEntity<>(deviceService.findDevice(id), HttpStatus.OK);			
	}
	
	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Device> createDevices(@RequestBody Device d) {
		return new ResponseEntity<>(deviceService.createOrUpdateDevice(d), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateDevices(@RequestBody Device d, @PathVariable Long id) {
		if(id==d.getId()) {
			return new ResponseEntity<>(deviceService.createOrUpdateDevice(d), HttpStatus.OK);			
		}else
			return new ResponseEntity<>("ERROR!! The device passed is not the same as the id specified in the URL", HttpStatus.OK);			
	}
}
