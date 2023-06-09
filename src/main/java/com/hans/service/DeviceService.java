package com.hans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hans.model.Device;
import com.hans.repository.DevicePageableRepository;
import com.hans.repository.DeviceRepository;

import jakarta.persistence.EntityExistsException;

@Service
public class DeviceService {

	@Autowired DeviceRepository db;
	@Autowired DevicePageableRepository dbPage;
	
	
	public Device createOrUpdateDevice(Device d) {
		
		return db.save(d);
	}
	
	
	
	public List<Device> findAllDevices(){
		return db.findAll();
	}
	
	public Page<Device> findAllDevicesPageable(Pageable p) {
		return dbPage.findAll(p);
	}
	
	public Device findDevice(Long id){
		if (DeviceExists(id)) {
			return db.findById(id).get();
		}else 
			throw new EntityExistsException("ERROR!! Device passed doesn't exist!");
	}
	
	public boolean DeviceExists(Long id) {
		if(db.existsById(id)) {
			return true;
		}else 
			return false;
	}
	
	boolean presenteDevice=false;
	public boolean DeviceExistsObj(Device d) {
		db.findAll().forEach(e->{
			if(e.getCode().equals(d.getCode())&&e.getState().equals(d.getState())&&e.getType().equals(d.getType())&&e.getId()==d.getId()) {
				presenteDevice=true;
			}
		});
		if(presenteDevice) {
			return true;
		}else 
			return false;
	}
}
